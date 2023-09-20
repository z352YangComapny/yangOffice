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
	AccessLog   *mongo.Collection
	ChatLog     *mongo.Collection
)

func InitMongoDB() error {
	clientOptions := options.Client().ApplyURI("mongodb://localhost:27017")
	var err error
	MongoClient, err = mongo.Connect(context.TODO(), clientOptions)
	if err != nil {
		log.Fatal("Failed to connect to MongoDB:", err)
		return err
	}
	ChatLog = MongoClient.Database("ssoystory").Collection("chat_log")
	AccessLog = MongoClient.Database("ssoystory").Collection("access_log")
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
