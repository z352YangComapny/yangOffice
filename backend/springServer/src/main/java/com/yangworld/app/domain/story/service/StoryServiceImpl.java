package com.yangworld.app.domain.story.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yangworld.app.domain.story.dto.StoryDto;
import com.yangworld.app.domain.story.dto.StoryMainDto;
import com.yangworld.app.domain.story.repository.StoryRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class StoryServiceImpl implements StoryService{

	@Autowired
	private StoryRepository storyRepository;
	
	@Override
	public int createStory(StoryDto storyDto) {
		return storyRepository.createStory(storyDto);
	}
	
	@Override
	public int updateStory(StoryMainDto storyDto) {
		return storyRepository.updateStory(storyDto);
	}
	@Override
	public int deleteStory(StoryMainDto storyDto) {
		return storyRepository.deleteStory(storyDto);
	}
	
	@Override
	public List<StoryMainDto> findStoryById(int id) {
		return storyRepository.findStoryById(id);
	}
	@Override
	public String findMemberUsername(int writerId) {
		return storyRepository.findMemberUsername(writerId);
	}
	
	@Override
	public List<StoryMainDto> findStoryByIdOnly(int writerId) {
		return storyRepository.findStoryByIdOnly(writerId);
	}
}
