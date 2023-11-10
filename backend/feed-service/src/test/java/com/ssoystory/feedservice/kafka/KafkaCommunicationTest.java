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

    @KafkaListener(topics = "feed-list-username-to-id-test", groupId = "ssoystory")
    public void receiveMessage (String message){
        receiveQueue.add(message);
    }
    public String receiveMessage () throws InterruptedException {
        return receiveQueue.take();
    }

    @Test
    void testKafkaCommunication() throws InterruptedException {
        // Given
        String topic = "feed-list-username-to-id-test";
        String message = String.format("{\"username\": \"%s\", \"pageNo\": %d}", "Tester01", 1);;

        // When
        kafkaTemplate.send(topic, message);
        Thread.sleep(1500);

        // Then
        String receivedMessage = receiveMessage();
        System.out.println(receivedMessage);
        assertThat(receivedMessage).isEqualTo(message);
    }
}
