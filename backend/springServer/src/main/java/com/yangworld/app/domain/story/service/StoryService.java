package com.yangworld.app.domain.story.service;

import java.util.List;

import com.yangworld.app.domain.story.dto.StoryDto;
import com.yangworld.app.domain.story.dto.StoryMainDto;

public interface StoryService {

	int createStory(StoryDto storyDto);

	int updateStory(StoryDto storyDto);

	int deleteStory(StoryDto storyDto);

	List<StoryMainDto> findStoryById(int id);

	String findMemberUsername(String writerId);

}
