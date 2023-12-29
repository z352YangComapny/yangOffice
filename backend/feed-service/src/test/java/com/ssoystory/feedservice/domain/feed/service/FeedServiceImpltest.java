package com.ssoystory.feedservice.domain.feed.service;

import com.google.gson.Gson;
import com.ssoystory.feedservice.domain.feed.dto.IdPageDto;
import com.ssoystory.feedservice.domain.feed.repository.FeedRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class FeedServiceImpltest {
    @Mock
    private FeedRepository feedRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private BlockingQueue<String> receiveQueue;
    @BeforeEach
    public void setup() {
        receiveQueue = new LinkedBlockingQueue<>();
    }


    @KafkaListener(topics = "feed-converted-username-to-id-test", groupId = "ssoystory")
    public void receiveMessage (String message){
        receiveQueue.add(message);
    }
    public String receiveMessage () throws InterruptedException {
        return receiveQueue.take();
    }

    @ParameterizedTest
    @CsvSource(value = {"tester01,1", "john_doe,1"})
    public void findPhotoFeedsByAuthorAndPageNOTest(String username, int pageNo) throws InterruptedException {
        //given
        String topic = "feed-convert-username-to-id";
        String message = String.format("{\"username\": \"%s\", \"pageNo\": %d}", username, pageNo);

        //when
        kafkaTemplate.send(topic,message);
        Thread.sleep(1500);
        String receiveMessage = receiveMessage();
        System.out.println(receiveMessage);

        Gson gson = new Gson();
        IdPageDto idPageDto = gson.fromJson(message,IdPageDto.class);
        System.out.println(idPageDto);

        if(username.equals("tester01"))
            assertThat(idPageDto.getUserId()).isEqualTo(3L);
        if(username.equals("john_doe"))
            assertThat(idPageDto.getUserId()).isEqualTo(2L);
    }
}
