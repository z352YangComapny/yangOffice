package game

import (
	"GoServer/db"
	"GoServer/internal/auth/jwt"
	"context"
	"encoding/json"
	"fmt"
	"github.com/gin-gonic/gin"
	"github.com/gorilla/websocket"
	"log"
	"net/http"
	"strings"
	"sync"
	"time"
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

			for key, client := range clients {
				if client == authData.Name {
					log.Println("이미 접속한 사용자 입니다.", err)
					errMsg := struct {
						MsgType string `json:"msgType"`
						ErrMsg  string `json:"errMsg"`
					}{
						MsgType: "errmsg",
						ErrMsg:  "이미 접속한 사용자 입니다.",
					}
					errMsgJson, _ := json.Marshal(errMsg)
					conn.WriteMessage(websocket.TextMessage, errMsgJson)

					safeClose(conn)
					delete(clients, conn)
					delete(clients, key)
					return
				}
			}
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
				MsgType string    `json:"msgType"`
				Name    string    `json:"name"`
				Time    time.Time `json:"time"`
			}{
				MsgType: "joinNotification",
				Name:    authData.Name,
				Time:    time.Now(), // 현재 시간을 가져와서 추가합니다.
			}
			joinNotification, _ := json.Marshal(nameMessage)
			_, err := db.AccessLog.InsertOne(context.TODO(), nameMessage)
			if err != nil {
				fmt.Println(err)
				ConnectErr := struct {
					MsgType string `json:"msgType"`
					ErrMsg  string `json:"errMsg"`
				}{
					MsgType: "errmsg",
					ErrMsg:  "Interval Server ERROR!",
				}
				errNotification, _ := json.Marshal(ConnectErr)
				conn.WriteMessage(websocket.TextMessage, errNotification)

				safeClose(conn)
				delete(clients, conn)
				return
			}
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

		var msgData struct {
			MsgType string `json:"msgType"`
			Text    string `json:"text"`
		}

		if err := json.Unmarshal(message, &msgData); err != nil {
			log.Println("Error decoding message:", err)
			continue // Skip processing this message
		}

		if msgData.MsgType == "chat" {
			// Insert the message into MongoDB

			currentTime := time.Now()
			chatMessage := struct {
				MsgType string    `json:"msgType"`
				Text    string    `json:"text"`
				Time    time.Time `json:"time"`
			}{
				MsgType: "chat",
				Text:    msgData.Text,
				Time:    currentTime,
			}
			_, err := db.ChatLog.InsertOne(context.TODO(), chatMessage)
			if err != nil {
				log.Println("Error inserting message into MongoDB:", err)
				continue // Skip processing this message
			}
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

		currentTime := time.Now()
		leaveMessage := struct {
			MsgType string    `json:"msgType"`
			Name    string    `json:"name"`
			Time    time.Time `json:"time"`
		}{
			MsgType: "leaveNotification",
			Name:    name,
			Time:    currentTime,
		}
		leaveNotification, _ := json.Marshal(leaveMessage)
		db.AccessLog.InsertOne(context.TODO(), leaveMessage)
		for client := range clients {
			err := client.WriteMessage(websocket.TextMessage, leaveNotification)
			if err != nil {
				log.Println(err)
			}
		}
	})
}
