package game

import (
	"GoServer/internal/auth/jwt"
	"encoding/json"
	"fmt"
	"github.com/gin-gonic/gin"
	"github.com/gorilla/websocket"
	"log"
	"net/http"
	"strings"
	"sync"
)

var (
	upgrader = websocket.Upgrader{
		CheckOrigin: func(r *http.Request) bool {
			return true
		},
	}
	clients = make(map[*websocket.Conn]string)
	readCh  = make(chan []byte)
	writeCh = make(chan []byte)
	mu      sync.Mutex
)

func HandleHandshake(c *gin.Context) {
	conn, err := upgrader.Upgrade(c.Writer, c.Request, nil)
	if err != nil {
		log.Println("failed to make handshake", err)
		return
	}

	HandleConnection(conn)
}

func HandleConnection(conn *websocket.Conn) {
	defer safeClose(conn)

	_, p, err := conn.ReadMessage()
	if err != nil {
		log.Println("Error reading message", err)
		safeClose(conn)
		delete(clients, conn)
		return
	}

	var authData struct {
		MsgType string `json:"msgType"`
		Token   string `json:"token"`
		Name    string `json:"name"`
	}

	if err := json.Unmarshal(p, &authData); err != nil {
		log.Println("Error decoding message:", err)
		safeClose(conn)
		delete(clients, conn)
		return
	}
	if authData.MsgType == "authorization" {
		var accessToken string
		if len(authData.Token) > 7 && strings.HasPrefix(authData.Token, "Bearer ") {
			accessToken = authData.Token[7:]
		} else {
			log.Println("token does not start with 'Bearer '")
			safeClose(conn)
			delete(clients, conn)
			return
		}
		authorities, err, _ := jwt.VerifyAccessToken(accessToken)
		if err != nil {
			log.Println("failed to verify token", err)
			safeClose(conn)
			delete(clients, conn)
			return
		}
		flag := false
		for _, authority := range authorities {
			if authority == "ROLE_USER" {
				flag = true
				break
			}
		}
		if flag {
			log.Println("Authorized 200")
			var connectedNames []string
			fmt.Println(clients)
			for conn := range clients {
				connectedNames = append(connectedNames, clients[conn])
			}
			fmt.Println(connectedNames)
			clients[conn] = authData.Name
			connectedNamesMessage := struct {
				MsgType       string   `json:"msgType"`
				ConnectedList []string `json:"connectedList"`
			}{
				MsgType:       "connectedList",
				ConnectedList: connectedNames,
			}

			connectedNamesJSON, _ := json.Marshal(connectedNamesMessage)
			conn.WriteMessage(websocket.TextMessage, connectedNamesJSON)

			nameMessage := struct {
				MsgType string `json:"msgType"`
				Name    string `json:"name"`
			}{
				MsgType: "joinNotification",
				Name:    authData.Name,
			}
			joinNotification, _ := json.Marshal(nameMessage)
			for client := range clients {
				err := client.WriteMessage(websocket.TextMessage, joinNotification)
				if err != nil {
					log.Println(err)
					delete(clients, client)
				}
			}

		} else {
			log.Println("unAuthorized 403")
			safeClose(conn)
			delete(clients, conn)
			return
		}

	} else {
		log.Println("can not find a token")
		delete(clients, conn)
		return
	}

	go readMessages(conn, readCh)
	go writeMessages(writeCh)

	for {
		select {
		case message := <-readCh:
			// 모든 클라이언트에게 메시지 브로드캐스트
			mu.Lock()
			for client := range clients {
				err := client.WriteMessage(websocket.TextMessage, message)
				if err != nil {
					log.Println(err)
					delete(clients, client)
				}
			}
			mu.Unlock()

			//지금 당사자에게 메세지를 보냄
		case message := <-writeCh:
			conn.WriteMessage(websocket.TextMessage, message)
		}
	}
}

func readMessages(conn *websocket.Conn, ch chan<- []byte) {
	for {
		_, message, err := conn.ReadMessage()
		if err != nil {
			safeClose(conn)
			delete(clients, conn)
			return
		}
		ch <- message
	}
}

func writeMessages(ch <-chan []byte) {
	for message := range ch {
		for client := range clients {
			err := client.WriteMessage(websocket.TextMessage, message)
			if err != nil {
				log.Println(err)
			}
		}
	}
}

func safeClose(conn *websocket.Conn) {
	var once sync.Once
	once.Do(func() {
		name := clients[conn]
		conn.Close()

		leaveMessage := struct {
			MsgType string `json:"msgType"`
			Name    string `json:"name"`
		}{
			MsgType: "leaveNotification",
			Name:    name,
		}
		leaveNotification, _ := json.Marshal(leaveMessage)
		for client := range clients {
			err := client.WriteMessage(websocket.TextMessage, leaveNotification)
			if err != nil {
				log.Println(err)
			}
		}
	})
}
