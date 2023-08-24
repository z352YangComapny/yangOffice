package com.yangworld.app.domain.story.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.story.dto.AttachmentProfileDto;
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
	    int id = Integer.parseInt(message.get("userId"));
//	    log.info("Received ID: {}", id);
		List<StoryMainDto> stories = storyService.findStoryById(id);
//		log.info("stories : {}", stories);
		
		List<AttachmentProfileDto> attachProfs = storyService.findAttachProf(id);
//		log.info("attachProfs = {}", attachProfs);
		
		String attach = "default.jpg";
 		
		List<Payload> payloads = new ArrayList<>();
		for(StoryMainDto story : stories) {
			String username = storyService.findMemberUsername(story.getWriterId());
			Payload tmp = Payload.builder()
				    .type(PayloadType.STORY)
				    .from(username)
				    .content(story.getContent())
				    .createdAt(story.getRegDate())
				    .build();
			payloads.add(tmp);
		}
		for(Payload payload : payloads) {
			int payloadId = storyService.findIdByUsername(payload.getFrom());
			for(AttachmentProfileDto attachProf : attachProfs) {
				if(payloadId == attachProf.getProfileId()) {
					payload.setAttach(attachProf.getRenamedFilename());
				}
			}
		}
		for(Payload payload : payloads) {
			if(payload.getAttach() == null) {
				payload.setAttach(attach);
			}
		}
//		log.info("payloads : {}", payloads);
		return payloads;
	}
}
