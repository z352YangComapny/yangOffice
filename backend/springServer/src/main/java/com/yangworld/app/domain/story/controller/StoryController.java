package com.yangworld.app.domain.story.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public void storyTap(@AuthenticationPrincipal PrincipalDetails principal, Model model) {
		List<StoryMainDto> stories = storyService.findStoryByIdOnly(principal.getId());
//		log.info("stories = {}", stories);
		model.addAttribute("stories", stories);
	}
	
	@GetMapping("/storyMain")
	public void storyMain() {}

	@PostMapping("/create")
	public String create(@Valid StoryDto storyDto){
//		log.info("storyDto = {}", storyDto);
		int result = storyService.createStory(storyDto);
		return "redirect:/story/storyTap";
	}
	
	@PostMapping("/update")
	public String update(@Valid StoryMainDto storyDto){
//		log.info("storyDto = {}", storyDto);
		int result = storyService.updateStory(storyDto);
		return "redirect:/story/storyTap";
	}
	
	@PostMapping("/delete")
	public String delete(StoryMainDto storyDto){
		int result = storyService.deleteStory(storyDto);
		return "redirect:/story/storyTap";
	}
}
