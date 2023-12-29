package com.ssoystory.guestbookservice.commons.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
@Slf4j
public class KafkaConsumerService {
    private final BlockingQueue<String> userIdQueue = new LinkedBlockingQueue<>();
    @KafkaListener(topics = "guestbook-converted-username-to-id", groupId = "ssoystory")
    public void receiveUserId(String message) {
        log.info("Received message from Kafka: {}", message);
        userIdQueue.add(message);
    }
    public String receiveUserId() throws InterruptedException {
        return userIdQueue.take();
    }
}