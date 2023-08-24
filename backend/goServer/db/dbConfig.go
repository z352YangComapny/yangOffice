package db

import (
	"context"
	"fmt"
	"github.com/redis/go-redis/v9"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
	"log"
)

var (
	MongoClient *mongo.Client
	RedisClient *redis.Client
)

func InitMongoDB() error {
	serverAPI := options.ServerAPI(options.ServerAPIVersion1)
	clientOptions := options.Client().ApplyURI("mongodb+srv://gryu:Xk06pOAu4BMJ5gMR@cluster0.ixt7euc.mongodb.net/?retryWrites=true&w=majority").SetServerAPIOptions(serverAPI)
	var err error
	MongoClient, err = mongo.Connect(context.TODO(), clientOptions)
	if err != nil {
		log.Fatal("Failed to connect to MongoDB:", err)
		return err
	}
	//Collection = MongoClient.Database("ssoystory").Collection("common")
	if err != nil {
		log.Fatal("디비 ping pong 실패")
	}
	return err
}

func InitRedis() error {
	RedisClient = redis.NewClient(&redis.Options{
		Addr: "localhost:6379",
		DB:   0,
	})

	_, err := RedisClient.Ping(context.Background()).Result()
	if err != nil {
		fmt.Errorf("failed to connect to Redis %v", err)
		return err
	}
	return nil
}
