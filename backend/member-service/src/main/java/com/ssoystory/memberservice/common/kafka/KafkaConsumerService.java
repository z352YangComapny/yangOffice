package com.ssoystory.memberservice.common.kafka;

import com.google.gson.Gson;
import com.ssoystory.memberservice.domain.member.dto.ConvertUsernameToIdDto;
import com.ssoystory.memberservice.domain.member.entity.Member;
import com.ssoystory.memberservice.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class KafkaConsumerService {
    private final KafkaProducerService kafkaProducerService;
    private final MemberRepository memberRepository;

    public KafkaConsumerService(KafkaProducerService kafkaProducerService, MemberRepository memberRepository) {
        this.kafkaProducerService = kafkaProducerService;
        this.memberRepository = memberRepository;
    }

//    @KafkaListener(topics = "feed-convert-username-to-id", groupId = "ssoystory")
    @KafkaListener(topics = "feed-convert-username-to-id", groupId = "ssoystory")
    public void receiveUserId(String message) {
        log.info("Received message from Kafka: {}", message);
        Gson gson = new Gson();
        ConvertUsernameToIdDto convertUsernameToIdDto = gson.fromJson(message, ConvertUsernameToIdDto.class);
        Optional<Member> member = memberRepository.findMemberByUsername(convertUsernameToIdDto.getUsername());
        kafkaProducerService.sendToFeedConvertUsernameToId(member.get().getId(),convertUsernameToIdDto.getPageNo());
    }

}