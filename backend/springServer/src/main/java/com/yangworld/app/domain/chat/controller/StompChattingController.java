package com.yangworld.app.domain.chat.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.yangworld.app.domain.chat.service.ChatService;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.service.MemberService;
import com.yangworld.app.domain.story.dto.Payload;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StompChattingController {

	@Autowired
	private ChatService chatService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/chat/{memberId}")
	@SendTo("/chatting")
	public Map<String, Object> chatAll(Payload _chat) {
		log.info("{}",_chat);
		Member member = memberService.findById(_chat.getId());
		Map<String, Object> map = new HashMap<>();
		map.put("id", _chat.getId());
		map.put("nickname",member.getNickname());
		map.put("content", _chat.getContent());
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);

		map.put("time", formattedTime);

		return map;
	}

	
}
