package com.ssoystory.memberservice.common.kafka;

import com.google.gson.Gson;
import com.ssoystory.memberservice.common.kafka.dto.IdDto;
import com.ssoystory.memberservice.domain.follow.repository.FollowRepository;
import com.ssoystory.memberservice.domain.member.dto.ConvertNicknameToIdDto;
import com.ssoystory.memberservice.domain.member.dto.ConvertUsernameToIdDto;
import com.ssoystory.memberservice.domain.member.entity.Member;
import com.ssoystory.memberservice.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class KafkaConsumerService {
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private FollowRepository followRepository;

//    @KafkaListener(topics = "feed-convert-username-to-id", groupId = "ssoystory")
    @KafkaListener(topics = "feed-convert-username-to-id", groupId = "ssoystory")
    public void receiveUserIdFromFeed(String message) {
        log.info("Received message from Kafka: {}", message);
        Gson gson = new Gson();
        ConvertUsernameToIdDto convertUsernameToIdDto = gson.fromJson(message, ConvertUsernameToIdDto.class);
        Optional<Member> member = memberRepository.findMemberByUsername(convertUsernameToIdDto.getUsername());
        kafkaProducerService.sendToFeedConvertUsernameToId(member.get().getId());
    }

    @KafkaListener(topics = "dm-convert-username-to-id", groupId = "ssoystory")
    public void receiveUserIdFromDM(String message) {
        log.info("Received message from Kafka: {}", message);
        Gson gson = new Gson();
        ConvertNicknameToIdDto convertNicknameToIdDto = gson.fromJson(message, ConvertNicknameToIdDto.class);
        Optional<Member> member = memberRepository.findMemberByNickname(convertNicknameToIdDto.getNickname());
        kafkaProducerService.sendToDmConvertNicknameToId(member.get().getId());
    }

    @KafkaListener(topics = "story-getFolloweeList-input", groupId = "ssoystory")
    public void receiveUserIdFromStory(String message) {
        log.info("Received message from Kafka: {}", message);
        Gson gson = new Gson();
        IdDto idDto = gson.fromJson(message, IdDto.class);
        List<Long> list = followRepository.getFolloweeIdListByFollowerId(idDto.getId());
        kafkaProducerService.sendToStoryFolloweeList(list);
    }

    @KafkaListener(topics = "guestbook-convert-username-to-id", groupId = "ssoystory")
    public void receiveUserIdFromGuestbook(String message) {
        log.info("Received message from Kafka: {}", message);
        Gson gson = new Gson();
        ConvertNicknameToIdDto convertNicknameToIdDto = gson.fromJson(message, ConvertNicknameToIdDto.class);
        Optional<Member> member = memberRepository.findMemberByNickname(convertNicknameToIdDto.getNickname());
        kafkaProducerService.sendToDmConvertNicknameToId(member.get().getId());
    }

}