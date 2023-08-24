package main

import (
	"GoServer/db"
	"GoServer/route"
	"log"
)

func main() {
	err := db.InitMongoDB()
	if err != nil {
		log.Fatalln("MongoDb 연결 실패", err)
	}
	err = db.InitRedis()
	if err != nil {
		log.Fatal("Redis 연결 실패", err)
	}
	router := route.SetupRouter()
	err = router.Run(":7070")
	if err != nil {
		log.Fatalln("서버 오픈 실패", err)
	}
}
