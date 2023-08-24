package middleware

import (
	"GoServer/internal/auth/jwt"
	"github.com/gin-gonic/gin"
	"log"
	"net/http"
	"strings"
)

func AuthMiddleware(role string) gin.HandlerFunc {
	return func(c *gin.Context) {
		tokenString := c.GetHeader("Authorization")
		var accessToken string
		if len(tokenString) > 7 && strings.HasPrefix(tokenString, "Bearer ") {
			accessToken = tokenString[7:]
		} else {
			log.Println("접속토큰 확인 실패")
			c.AbortWithStatusJSON(http.StatusUnauthorized, gin.H{"message": "Unauthorized"})
			return
		}

		authorities, err, newAcc := jwt.VerifyAccessToken(accessToken)
		if err != nil {
			log.Println("검증 실패", err)
			c.AbortWithStatusJSON(http.StatusUnauthorized, gin.H{"message": "Unauthorized"})
			return
		}
		if len(newAcc) > 0 {
			c.Header("Authorization", "Bearer "+newAcc)
		}
		authorized := false
		for _, authority := range authorities {
			if authority == role {
				authorized = true
			}
		}
		if authorized {
			log.Println("인가 성공", role)
			c.Next()
		} else {
			c.AbortWithStatusJSON(http.StatusUnauthorized, gin.H{"message": "Unauthorized"})
		}
	}
}
