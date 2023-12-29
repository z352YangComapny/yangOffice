package com.ssoystory.storyservice.common.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
@Slf4j
public class KafkaConsumerService {
    private final BlockingQueue<String> userIdQueue = new LinkedBlockingQueue<>();
    @KafkaListener(topics = "story-getFolloweeList-output", groupId = "ssoystory")
    public void receiveFollowList(String message) {
        log.info("Received message from Kafka: {}", message);
        userIdQueue.add(message);
    }
    public String receiveFolloweeList() throws InterruptedException {
        return userIdQueue.take();
    }
}