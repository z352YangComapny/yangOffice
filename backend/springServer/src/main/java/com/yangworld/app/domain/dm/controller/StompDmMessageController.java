package com.yangworld.app.domain.dm.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.yangworld.app.domain.story.dto.Payload;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StompDmMessageController {

//	@MessageMapping("/notice")
//	@SendTo("/dm/notice")
//	public Payload notice(Payload notification) {
//		log.debug("notification = {}", notification);
//		// notificationService.insertNotification(message);
//		return notification;
//	}
	
	@MessageMapping("/notice/{userId}")
	@SendTo("/dm/notice/{userId}")
	public Payload noticeEach(@DestinationVariable String userId, Payload notification) {
		log.debug("userId = {}", userId);
		log.debug("notification = {}", notification);
		return notification;
	}
	
}
