package com.ssoystory.feedservice.common.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendUsernameAndPageNo(String username, int pageNo) {
        String message = String.format("{\"username\": \"%s\"", username);
        kafkaTemplate.send("feed-convert-username-to-id", message);
    }
}
