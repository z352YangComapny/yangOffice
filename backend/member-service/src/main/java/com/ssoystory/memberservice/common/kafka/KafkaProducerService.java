package com.ssoystory.memberservice.common.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendToFeedConvertUsernameToId(Long userId) {
        String message = String.format("{\"userId\": %d ", userId);
        kafkaTemplate.send("feed-converted-username-to-id", message);
    }

    public void sendToDmConvertNicknameToId(Long userId) {
        String message = String.format("{\"userId\": %d ", userId);
        kafkaTemplate.send("dm-converted-username-to-id", message);
    }
}