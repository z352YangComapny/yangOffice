package com.yangworld.app.domain.story.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;



import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.story.dto.Payload;
import com.yangworld.app.domain.story.dto.PayloadType;
import com.yangworld.app.domain.story.dto.StoryDto;
import com.yangworld.app.domain.story.dto.StoryMainDto;
import com.yangworld.app.domain.story.service.StoryService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StoryStompController {
	
	@Autowired
	private StoryService storyService;
	
	@MessageMapping("/main")
	@SendTo("/story/main")
	public List<Payload> story(Payload payload, @AuthenticationPrincipal PrincipalDetails principal) {
		log.debug("payload = {}", payload);
		log.debug("id = {}", principal.getId());
		List<StoryMainDto> stories = storyService.findStoryById(principal.getId());
		List<Payload> payloads = new ArrayList<>();
		for(StoryMainDto story : stories) {
			Payload tmp = Payload.builder()
				    .type(PayloadType.STORY)
				    .from(story.getWriterId())
				    .content(story.getContent())
				    .createdAt(story.getRegDate())
				    .build();
			payloads.add(tmp);
		}
		return payloads;
	}
	
}
