package com.yangworld.app.domain.story.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yangworld.app.domain.story.dto.StoryDto;
import com.yangworld.app.domain.story.repository.StoryRepository;

@Service
public class StoryServiceImpl implements StoryService{

	@Autowired
	private StoryRepository storyRepository;
	
	@Override
	public int createStory(StoryDto storyDto) {
		return storyRepository.createStory(storyDto);
	}
	
	@Override
	public int updateStory(StoryDto storyDto) {
		return storyRepository.updateStory(storyDto);
	}
	@Override
	public int deleteStory(StoryDto storyDto) {
		return storyRepository.deleteStory(storyDto);
	}
}
