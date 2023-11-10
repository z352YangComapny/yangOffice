package com.ssoystory.feedservice.kafka;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9099"})
public class KafkaCommunicationTest {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private BlockingQueue<String> receiveQueue;

    @BeforeEach
    public void setup() {
        receiveQueue = new LinkedBlockingQueue<>();
    }

    @KafkaListener(topics = "test", groupId = "ssoystory")
    public void receiveMessage (String message){
        receiveQueue.add(message);
    }
    public String receiveMessage () throws InterruptedException {
        return receiveQueue.take();
    }

    @Test
    void testKafkaCommunication() throws InterruptedException {
        // Given
        String topic = "test";
        String message = "Hello, Kafka!";

        // When
        kafkaTemplate.send(topic, message);
        Thread.sleep(1000); // Wait for Kafka to process the message

        // Then
        // Add assertions or checks to verify the expected behavior
        String receivedMessage = receiveMessage();
        assertThat(receivedMessage).isEqualTo(message);

    }
}
