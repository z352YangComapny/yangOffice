package com.yangworld.app.domain.story.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.yangworld.app.domain.member.dto.FollowDto;
import com.yangworld.app.domain.member.service.MemberService;
import com.yangworld.app.domain.story.dto.AttachmentProfileDto;
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
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/init/{userId}")
	@SendTo("/storyMain/{userId}")
	public List<Payload> story(@DestinationVariable String userId, @org.springframework.messaging.handler.annotation.Payload Map<String, String> message) {
	    int id = Integer.parseInt(message.get("userId"));

    	List<StoryMainDto> stories = storyService.findStoryById(id);
//    	@Select("select 
//    				* 
//    			from 
//    				(select 
//    					* 
//    				from 
//    					story 
//    				where 
//    					writer_id = #{id} and reg_date >= (sysdate - 1) 
//    					
//    				union 
//    				
//    				select 
//    					s.* 
//    				from 
//    					story s join follow f
//    						on s.writer_id = f.followee 
//    				where 
//    					f.follower = #{id} 
//    					and 
//    					s.reg_date >= (sysdate - 1)) 
//    			order by reg_date")
    	
    	List<AttachmentProfileDto> attachProfs = storyService.findAttachProf(id);
    	
    	String attach = "default.jpg";
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
    	
    	List<Payload> payloads = new ArrayList<>();
    	for(StoryMainDto story : stories) {
    		story.setFormattedRegDate((story.getRegDate()).format(formatter));
    		try {
    			int feed = storyService.findStoryFeedByStoryId(story.getId());
    			story.setStoryFeed(feed);
    			
    		} catch (Exception ignore) {}
    		String username = storyService.findMemberUsername(story.getWriterId());

    		Payload tmp = Payload.builder()
    				.type(PayloadType.STORY)
    				.from(username)
    				.content(story.getContent())
    				.formattedCreatedAt(story.getFormattedRegDate())
    				.id(story.getId())
    				.storyFeed(story.getStoryFeed())
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

    	return payloads;
    }
	
	@MessageMapping("/create/{userId}")
	public void storyCreate(@DestinationVariable String userId, @org.springframework.messaging.handler.annotation.Payload Map<String, String> message) {
	    // 스토리 생성
		int id = Integer.parseInt(message.get("userId"));
	    String content = message.get("content");
	    StoryDto storyDto = StoryDto.builder()
	    		.writerId(id)
	    		.content(content)
	    		.build();
	    storyService.createStory(storyDto);
	    
	    // 나를 팔로우한 사람
	    List<FollowDto> followers = memberService.findFollowerById(id);
	    FollowDto user = new FollowDto();
	    user.setFollower(id);
	    followers.add(user);
	    
	    for(FollowDto follower : followers) {
	    	log.info("user = {}", follower.getFollower());
	    	List<StoryMainDto> stories = storyService.findStoryById(follower.getFollower());
			log.info("stories : {}", stories);
	    	
	    	List<AttachmentProfileDto> attachProfs = storyService.findAttachProf(follower.getFollower());
	    	
	    	String attach = "default.jpg";
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
	    	
	    	List<Payload> payloads = new ArrayList<>();
	    	for(StoryMainDto story : stories) {
	    		story.setFormattedRegDate((story.getRegDate()).format(formatter));
	    		try {
	    			int feed = storyService.findStoryFeedByStoryId(story.getId());
	    			story.setStoryFeed(feed);
	    			
	    		} catch (Exception ignore) {}
	    		String username = storyService.findMemberUsername(story.getWriterId());
	    		
	    		Payload tmp = Payload.builder()
	    				.type(PayloadType.STORY)
	    				.from(username)
	    				.content(story.getContent())
	    				.formattedCreatedAt(story.getFormattedRegDate())
	    				.id(story.getId())
	    				.storyFeed(story.getStoryFeed())
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
	    	
	    	// forEach를 통해 id별로 payloads를 만든 후 각각의 심플브로커에 송신
	    	String channel = "/storyMain/" + follower.getFollower();
	    	messagingTemplate.convertAndSend(channel, payloads);
	    }
	}
}
