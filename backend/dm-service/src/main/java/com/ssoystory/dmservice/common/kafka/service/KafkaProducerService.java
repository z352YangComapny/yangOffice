package com.ssoystory.dmservice.common.kafka.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendNickname(String nickname) {
        String message = String.format("{\"nickname\": \"%s\"}", nickname);
        kafkaTemplate.send("dm-convert-username-to-id", message);
    }
}
