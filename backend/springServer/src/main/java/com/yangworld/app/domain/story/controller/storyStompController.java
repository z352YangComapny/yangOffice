package com.yangworld.app.domain.story.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import com.yangworld.app.domain.story.dto.StoryDto;
import com.yangworld.app.domain.story.entity.Payload;
import com.yangworld.app.domain.story.entity.PayloadType;
import com.yangworld.app.domain.story.service.StoryService;

public class storyStompController {
	
	@Autowired
	private StoryService storyService;

	@MessageMapping("/send")
	@SendTo("/storyMain")
	public List<Payload> story(@org.springframework.messaging.handler.annotation.Payload Map<String, String> message) {
	    int id = Integer.parseInt(message.get("userId"));
//	    log.info("Received ID: {}", id);
		List<StoryDto> stories = storyService.findStoryById(id);
//		log.info("stories : {}", stories);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
		
		List<Payload> payloads = new ArrayList<>();
		for(StoryDto story : stories) {
			story.setFormattedRegDate((story.getRegDate()).format(formatter));
			String username = storyService.findMemberUsername(story.getWriterId());
//			log.info("username = {}", username);
			Payload tmp = Payload.builder()
				    .type(PayloadType.STORY)
				    .writerId(username)
				    .content(story.getContent())
				    .formattedCreatedAt(story.getFormattedRegDate())
				    .id(story.getId())
				    .build();
			payloads.add(tmp);
		}

//		log.info("payloads : {}", payloads);
		return payloads;
	}

}
