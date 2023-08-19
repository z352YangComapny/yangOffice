package com.yangworld.app.domain.story.service;

import com.yangworld.app.domain.story.dto.StoryDto;

public interface StoryService {

	int createStory(StoryDto storyDto);

	int updateStory(StoryDto storyDto);

	int deleteStory(StoryDto storyDto);

    int getTotalStoryCount();
}
