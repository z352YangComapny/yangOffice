package com.yangworld.app.domain.story.service;

import com.yangworld.app.domain.story.dto.StoryAdminDto;
import com.yangworld.app.domain.story.dto.StoryDailyDto;
import com.yangworld.app.domain.story.dto.StoryDto;
import com.yangworld.app.domain.story.entity.Story;

import java.util.List;

public interface StoryService {

	int createStory(StoryDto storyDto);

	int updateStory(StoryDto storyDto);

	int deleteStory(StoryDto storyDto);

    int getTotalStoryCount();

	List<StoryAdminDto> getAdminStory(int pageNo, int pageSize);

    Story findStoryOriginById(int storyId);

	List<StoryDailyDto> findStoryDaily();

	String findMemberUsername(int writerId);

	List<StoryDto> findStoryById(int id);
}
