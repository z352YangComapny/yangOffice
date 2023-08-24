package tests

import (
	"GoServer/db"
	"GoServer/internal/auth/jwt"
	"fmt"
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestVerifyAccessToken(t *testing.T) {
	db.InitRedis()
	var testToken = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0ZXN0MSIsImV4cCI6MTY5MTczMzMxNCwiaWQiOjEsInVzZXJuYW1lIjoidGVzdDEiLCJhdXRob3JpdGllcyI6IltST0xFX0FETUlOLCBST0xFX01BTkFHRVIsIFJPTEVfVVNFUl0ifQ.y9TQI813T7x79s2CSxqhuv-j4hinQSgb_EhLTr5dnb2nyaZ95g63qa0cTl4AR6xRZqeRmsRyhCz0OWPzURNjZQ"
	// verifyAccessToken 테스트
	valid, err, _ := jwt.VerifyAccessToken(testToken)
	if err != nil {
		assert.Nil(t, err, "Failed to verify token: ", err)
	}
	fmt.Println("Token is valid:", valid)
}

func TestCreateAccessToken(t *testing.T) {
	claims := &jwt.TempClaims{
		Id:          5,
		Username:    "user",
		Authorities: "[ROLE_ADMIN, ROLE_MANAGER, ROLE_USER]",
	}
	token, err := jwt.CreateAccessToken(claims)
	fmt.Println(token)
	assert.Nil(t, err, "Failed to test : ", err)

}
