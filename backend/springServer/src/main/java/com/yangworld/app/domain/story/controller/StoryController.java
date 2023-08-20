package com.yangworld.app.domain.story.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.story.dto.Payload;
import com.yangworld.app.domain.story.dto.PayloadType;
import com.yangworld.app.domain.story.dto.StoryDto;
import com.yangworld.app.domain.story.dto.StoryMainDto;
import com.yangworld.app.domain.story.entity.Story;
import com.yangworld.app.domain.story.service.StoryService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/story")
@Slf4j
public class StoryController {
	
	@Autowired
	private StoryService storyService;
	
	@GetMapping("/storyTap")
	public void storyTap(Model model) {
//		Story story = storyService.findStoryById();
//		model.addAttribute("story", story); 로그인멤버 id 받아서 처리
	}
	
	@GetMapping("/storyMain")
	public void storyMain() {}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody StoryDto storyDto){
		int result = storyService.createStory(storyDto);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody StoryDto storyDto){
		int result = storyService.updateStory(storyDto);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody StoryDto storyDto){
		int result = storyService.deleteStory(storyDto);
		return ResponseEntity.ok().build();
	}
}
