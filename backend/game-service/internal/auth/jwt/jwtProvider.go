package jwt

import (
	"GoServer/db"
	"context"
	"encoding/json"
	"fmt"
	"github.com/golang-jwt/jwt/v5"
	"strconv"
	"strings"
	"time"
)

var (
	secretKey = []byte("양소영그는신인가")
)

type Authority struct {
	Authority string `json:"authority"`
}

type Claims struct {
	Id          int         `json:"id"`
	Username    string      `json:"username"`
	Authorities []Authority `json:"authorities"`
	jwt.RegisteredClaims
}
type TempClaims struct {
	Id          int    `json:"id"`
	Username    string `json:"username"`
	Authorities string `json:"authorities"`
	jwt.RegisteredClaims
}

func CreateAccessToken(claims *TempClaims) (string, error) {
	claims.RegisteredClaims.ExpiresAt = jwt.NewNumericDate(time.Now().Add(2 * time.Hour))
	token := jwt.NewWithClaims(jwt.SigningMethodHS512, claims)
	accessToken, err := token.SignedString(secretKey)
	if err != nil {
		return "", err
	}
	return accessToken, err
}

func VerifyAccessToken(accessToken string) ([]string, error, string) {
	var authorities []string
	var newAcctoken string

	//log.Println(accessToken)

	accToken, err := jwt.Parse(accessToken, func(token *jwt.Token) (interface{}, error) {
		if _, ok := token.Method.(*jwt.SigningMethodHMAC); !ok {
			return nil, fmt.Errorf("unexpected signing method: %v", token.Header["alg"])
		}
		return secretKey, nil
	})
	if err != nil {
		return nil, err, ""
	}

	if accClaims, ok := accToken.Claims.(jwt.MapClaims); ok && accToken.Valid {
		//RefreshToken 과 비교
		id, ok := accClaims["id"].(float64)
		if !ok {
			return nil, fmt.Errorf("accToken Error"), ""
		}
		username, ok := accClaims["usernmae"].(string)
		// 권한 정보 파싱
		authoritiesRaw, ok := accClaims["authorities"].(string)
		if len(authoritiesRaw) > 0 {
			authoritiesRaw = strings.Replace(authoritiesRaw, " ", "", -1)
			authoritiesRaw = authoritiesRaw[1 : len(authoritiesRaw)-1]
			authorities = strings.Split(authoritiesRaw, ",")
		} else {
			return nil, fmt.Errorf("cannot find authorities"), ""
		}

		//

		_id := "RefreshToken:" + strconv.Itoa(int(id))

		refreshTokenStr, err := db.RedisClient.Get(context.Background(), _id).Result()
		if err != nil {
			return nil, fmt.Errorf("can not find the RefreshToken %v", err), ""
		}

		var data map[string]interface{}
		if err := json.Unmarshal([]byte(refreshTokenStr), &data); err != nil {
			return nil, fmt.Errorf("JSON parsing error %v", err), ""
		}

		tokenVal := data["tokenVal"].(string)
		refToken, err := jwt.Parse(tokenVal, func(token *jwt.Token) (interface{}, error) {
			if _, ok := token.Method.(*jwt.SigningMethodHMAC); !ok {
				return nil, fmt.Errorf("unexpected signing method: %v", token.Header["alg"])
			}
			return secretKey, nil
		})
		if err != nil {
			return nil, err, ""
		}
		refClaims, ok := refToken.Claims.(jwt.MapClaims)
		///

		// 만료 시간 체크 추가
		refExp, ok := refClaims["exp"].(float64)
		exp, ok := accClaims["exp"].(float64)
		if !ok || time.Now().Unix() > int64(exp) {
			if !ok || time.Now().Unix() < int64(refExp) {
				auth := "["
				for _, value := range authorities {
					auth += value
					auth += ", "
				}
				auth = auth[0:len(auth)-2] + "]"
				input := &TempClaims{
					Id:          int(id),
					Username:    username,
					Authorities: auth,
				}
				fmt.Println(auth)
				newAcctoken, err = CreateAccessToken(input)
				if err != nil {
					return nil, fmt.Errorf("failed to refresh access Token"), ""
				}

			} else {
				return nil, fmt.Errorf("refToken expired"), ""
			}
		}

	} else {
		return nil, fmt.Errorf("invalid accToken"), ""
	}
	return authorities, nil, newAcctoken
}
