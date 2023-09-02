package com.yangworld.app.domain.story.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yangworld.app.domain.story.dto.StoryDto;
import com.yangworld.app.domain.story.service.StoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/story")
@Slf4j
public class StoryController {
	
	@Autowired
	private StoryService storyService;
	
	@PatchMapping("/storyUpdate")
	public ResponseEntity<?> update(@RequestBody StoryDto storyDto){
		int result = storyService.updateStory(storyDto);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/storyDelete")
	public ResponseEntity<?> delete(@RequestBody StoryDto storyDto){
		int result = storyService.deleteStory(storyDto);
		return ResponseEntity.noContent().build();
	}
}
