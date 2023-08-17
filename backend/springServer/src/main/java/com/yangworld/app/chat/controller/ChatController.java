package com.yangworld.app.chat.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.yangworld.app.chat.dto.Payload;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ChatController {
	
	@MessageMapping("/chatAll")
	@SendTo("/chatAll/all")
	public Payload chat(Payload message) {
		log.debug("message = {}", message);
		return message;
	}
}
