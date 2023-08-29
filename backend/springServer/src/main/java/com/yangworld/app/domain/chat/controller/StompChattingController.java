package com.yangworld.app.domain.chat.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.service.MemberService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	MemberService memberService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/chat")
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
		
//	    List<ChatListDto> chats = chatService.findChatList();
//
//	    List<Payload> payloads = new ArrayList<>();
//
//	    for (ChatListDto chat : chats) {
//	        int chatMemberId = chat.getMemberId(); // 채팅의 memberId
//	        String username = chatService.findById(chatMemberId); // 채팅의 memberId로 username 조회
//	        Payload payload = Payload.builder()
//	                .type(PayloadType.CHAT)
//	                .from(username)
//	                .content(chat.getContent())
//	                .createdAt(chat.getSendDate())
//	                .id(chat.getId())
//	                .build();
//
//	        payloads.add(payload);
//	    }
		return map;
	}


}
