package com.ssoystory.storyservice.domain.story.service;

import com.ssoystory.storyservice.domain.story.dto.CreateStoryDto;
import com.ssoystory.storyservice.domain.story.entity.Story;

import java.util.List;

public interface StoryService {


    List<Story> getStoryList(Long id);

    void save(long id, CreateStoryDto createStoryDto);

    void deleteById(Long id, long storyId);
}
