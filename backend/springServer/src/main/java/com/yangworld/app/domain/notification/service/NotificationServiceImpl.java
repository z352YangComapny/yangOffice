package com.yangworld.app.domain.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.yangworld.app.domain.dm.entity.Dm;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.repository.MemberRepository;
import com.yangworld.app.domain.story.dto.Payload;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;
	
	@Autowired
    MemberRepository memberRepository;
	
	@Override
	public int notifySendDm(Dm msg) {
		
		int to = msg.getReceiverId();
		int from = msg.getSenderId();
		
		Member sender = memberRepository.findById(from);
		
        if (sender != null) {
            String username = sender.getNickname();
            
            Payload payload = Payload.builder()
                    .to(to)
                    .content(username + "님이 새로운 DM을 보냈습니다.")
                    .build();

            simpMessagingTemplate.convertAndSend("/dm/notice/" + to, payload); 
        } else {
            log.error("Receiver not found with id: {}", to);
        }

        return 0;
	}

}
