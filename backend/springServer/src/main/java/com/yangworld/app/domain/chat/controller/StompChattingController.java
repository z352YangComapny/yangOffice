package com.yangworld.app.domain.chat.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.yangworld.app.domain.dm.controller.StompDmMessageController;
import com.yangworld.app.domain.story.dto.Payload;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StompChattingController {

	@MessageMapping("/chat/{memberId}")
	@SendTo("/chat/chatting/{memberId}")
	public Payload chatEach(@DestinationVariable String memberId, Payload chat) {
		log.debug("memberId = {}", memberId);
		log.debug("chat = {}", chat);
		return chat;
	}
	
}
