package route

import (
	"GoServer/internal/auth/middleware"
	"GoServer/internal/game"
	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
	"log"
	"net/http"
)

func SetupRouter() *gin.Engine {
	router := gin.Default()
	router.GET("/ping", func(context *gin.Context) {
		context.JSON(http.StatusOK, gin.H{"message": "pong"})
	})
	router.GET("/user", middleware.AuthMiddleware("ROLE_USER"), func(context *gin.Context) {
		context.JSON(http.StatusOK, gin.H{"message": "user"})
	})
	router.GET("/manager", middleware.AuthMiddleware("ROLE_MANAGER"), func(context *gin.Context) {
		context.JSON(http.StatusOK, gin.H{"message": "manger"})
	})
	router.GET("/admin", middleware.AuthMiddleware("ROLE_ADMIN"), func(context *gin.Context) {
		context.JSON(http.StatusOK, gin.H{"message": "admin"})
	})
	router.GET("/ws", game.HandleHandshake)

	trustedProxies := []string{
		"127.0.0.1",
	}
	err := router.SetTrustedProxies(trustedProxies)
	if err != nil {
		log.Fatal("proxies allowed err", err)
	}
	router.Use(cors.New(cors.Config{
		AllowOrigins:     []string{"http://localhost:3000", "http://localhost:8080"},
		AllowMethods:     []string{"PUT", "PATCH", "POST", "DELETE"},
		AllowHeaders:     []string{"Origin", "Authorization", "Content-Type"},
		ExposeHeaders:    []string{"Content-Length", "Authorization"},
		AllowCredentials: true,
	}))
	return router
}
