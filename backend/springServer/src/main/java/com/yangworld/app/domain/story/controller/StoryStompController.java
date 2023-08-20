package com.yangworld.app.domain.story.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.story.dto.Payload;
import com.yangworld.app.domain.story.dto.PayloadType;
import com.yangworld.app.domain.story.dto.StoryMainDto;
import com.yangworld.app.domain.story.service.StoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StoryStompController {
	
	@Autowired
	private StoryService storyService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/send")
	@SendTo("/storyMain")
	public List<Payload> story(@org.springframework.messaging.handler.annotation.Payload Map<String, String> message) {
	    String id = message.get("userId");
	    log.debug("Received ID: {}", id);
//		List<StoryMainDto> stories = storyService.findStoryById(principal.getId());
		List<Payload> payloads = new ArrayList<>();
//		for(StoryMainDto story : stories) {
//			Payload tmp = Payload.builder()
//				    .type(PayloadType.STORY)
//				    .from(story.getWriterId())
//				    .content(story.getContent())
//				    .createdAt(story.getRegDate())
//				    .build();
//			payloads.add(tmp);
//		}
		return payloads;
	}
}
