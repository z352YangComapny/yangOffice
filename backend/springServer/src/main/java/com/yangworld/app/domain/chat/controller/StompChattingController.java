package com.yangworld.app.domain.chat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.yangworld.app.domain.chat.dto.ChatListDto;
import com.yangworld.app.domain.chat.service.ChatService;
import com.yangworld.app.domain.story.dto.Payload;
import com.yangworld.app.domain.story.dto.PayloadType;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StompChattingController {

	@Autowired
	private ChatService chatService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/chat/{memberId}")
	@SendTo("/chatting")
	public List<Payload> chatEach(@PathVariable int memberId, @org.springframework.messaging.handler.annotation.Payload Map<String, String> _chat) {
	    List<ChatListDto> chats = chatService.findChatList();
	    
	    List<Payload> payloads = new ArrayList<>();
	    
	    for (ChatListDto chat : chats) {
	        int chatMemberId = chat.getMemberId(); // 채팅의 memberId
	        String username = chatService.findById(chatMemberId); // 채팅의 memberId로 username 조회
	        Payload payload = Payload.builder()
	                .type(PayloadType.CHAT)
	                .from(username)
	                .content(chat.getContent())
	                .createdAt(chat.getSendDate())
	                .id(chat.getId())
	                .build();
	        
	        payloads.add(payload);
	    }
	    return payloads;
	}

	
}
