package com.ssoystory.storyservice.domain.story.controller;

import com.ssoystory.storyservice.domain.story.dto.CreateStoryDto;
import com.ssoystory.storyservice.domain.story.dto.DeleteStoryDto;
import com.ssoystory.storyservice.domain.story.entity.Story;
import com.ssoystory.storyservice.domain.story.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/story")
public class StoryController {
    @Autowired
    private StoryService storyService;

    @GetMapping
    ResponseEntity<List<Story>> getStoryList(@RequestHeader("x-authorization-id") long id){
        try {
            List<Story> list = storyService.getStoryList(id);
            return ResponseEntity.ok().body(list);
        } catch (InterruptedException e){
            return ResponseEntity.internalServerError().build();
        }

    }

    @PostMapping
    ResponseEntity<?> createStory(@RequestHeader("x-authorization-id") long id, @RequestBody CreateStoryDto createStoryDto){
        storyService.save(id, createStoryDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    ResponseEntity<?> deleteStory(@RequestHeader("x-authorization-id") long id, @RequestBody DeleteStoryDto storyDto){
        storyService.deleteById(storyDto.getId(),id);
        return ResponseEntity.ok().build();
    }
}
