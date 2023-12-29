package com.yangworld.app.domain.story.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.member.dto.FollowDto;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.service.MemberService;
import com.yangworld.app.domain.photoFeed.dto.PhotoAttachmentFeedDto;
import com.yangworld.app.domain.photoFeed.service.PhotoFeedService;
import com.yangworld.app.domain.profile.entity.ProfileDetails;
import com.yangworld.app.domain.profile.entity.State;
import com.yangworld.app.domain.profile.service.ProfileService;
import com.yangworld.app.domain.story.dto.AttachmentProfileDto;
import com.yangworld.app.domain.story.dto.Payload;
import com.yangworld.app.domain.story.dto.PayloadType;
import com.yangworld.app.domain.story.dto.StoryDto;
import com.yangworld.app.domain.story.dto.StoryMainDto;
import com.yangworld.app.domain.story.service.StoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/story")
@Slf4j
public class StoryController {
	
	@Autowired
	private StoryService storyService;
	
    @Autowired
    private PhotoFeedService photoFeedService; 
    
    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private MemberService memberService;
    
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@GetMapping("/storyTap")
	public void storyTap(@AuthenticationPrincipal PrincipalDetails principal, Model model) {
		List<StoryMainDto> stories = storyService.findStoryByIdOnly(principal.getId());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm");
		for (StoryMainDto story : stories) {
			story.setFormattedRegDate((story.getRegDate()).format(formatter));
			try {
				int feed = storyService.findStoryFeedByStoryId(story.getId());
				story.setStoryFeed(feed);
			} catch (Exception ignore) {}
		}
		int id = principal.getId();
        Member member = memberService.findById(id);
        model.addAttribute("member", member);
//		log.info("stories = {}", stories);
        ProfileDetails profile = profileService.getProfileByMemberId(id);
//      log.info("profile={}", profile);

		List<PhotoAttachmentFeedDto> photoList = photoFeedService.selectFeed(id);
        List<Attachment> profileAttachments =null;
        if(profile !=null){
            // 프로필 사진 가져오기
            profileAttachments = profileService.getAttachmentsByProfileId(profile.getId());
            model.addAttribute("id",id);
            
    	    model.addAttribute("photoList", photoList);
            log.info("profileAttachments={}", profileAttachments);
            model.addAttribute("profile", profile);
            model.addAttribute("profileAttachments", profileAttachments);
            model.addAttribute("principalBday", member.getBirthday());
            model.addAttribute("principalName", member.getName());
            model.addAttribute("PrincipalDetails", principal);
            log.info("profile = {}", profile);
            log.info("profileAttachment = {}", profileAttachments);
        } else{
            profile = ProfileDetails.builder()
                    .attachments(null)
                    .state(State.A)
                    .introduction("새롭게 작성해주세요")
                    .build();
        }
       // log.info("profileAttachment={}", profileAttachments);
        model.addAttribute("profileAttachments", profileAttachments);
        model.addAttribute("profile", profile);
		model.addAttribute("stories", stories);
	}
	
	@GetMapping("/storyFeedFind")
	public String findFeed(Model model, @AuthenticationPrincipal PrincipalDetails principal){
		List<PhotoAttachmentFeedDto> photoList = photoFeedService.selectFeed(principal.getId());
	    model.addAttribute("photoList", photoList);
		return "story/storyFeed";
	}
	
	@GetMapping("/storyMain")
	public void storyMain() {}

//	@PostMapping("/create")
//	public String create(@Valid StoryDto storyDto){
////		log.info("storyDto = {}", storyDto);
//		int result = storyService.createStory(storyDto);
//		return "redirect:/story/storyTap";
//	}
	
	@PostMapping("/update")
	public String update(@Valid StoryMainDto storyDto){
		log.info("storyDto = {}", storyDto);
		int result = storyService.updateStory(storyDto);
		
	    List<FollowDto> followers = memberService.findFollowerById(storyDto.getWriterId());
	    FollowDto user = new FollowDto();
	    user.setFollower(storyDto.getWriterId());
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
		return "redirect:/story/storyTap";
	}
	
	@PostMapping("/delete")
	public String delete(StoryMainDto storyDto){
		int result = storyService.deleteStory(storyDto);

	    List<FollowDto> followers = memberService.findFollowerById(storyDto.getWriterId());
	    FollowDto user = new FollowDto();
	    user.setFollower(storyDto.getWriterId());
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
		return "redirect:/story/storyTap";
	}
}
