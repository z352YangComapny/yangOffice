package com.ssoystory.memberservice.common.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendToFeedConvertUsernameToId(Long userId) {
        String message = String.format("{\"userId\": %d }", userId);
        kafkaTemplate.send("feed-converted-username-to-id", message);
    }

    public void sendToDmConvertNicknameToId(Long userId) {
        String message = String.format("{\"userId\": %d }", userId);
        kafkaTemplate.send("dm-converted-username-to-id", message);
    }

    public void sendToStoryFolloweeList(List<Long> list) {
        String message = String.format("{\"followeeList\": %s }", list.toString());
        kafkaTemplate.send("story-getFolloweeList-output", message);
    }
}