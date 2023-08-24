package com.yangworld.app.domain.story.service;

import java.util.List;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.story.dto.AttachmentProfileDto;
import com.yangworld.app.domain.story.dto.StoryDto;
import com.yangworld.app.domain.story.dto.StoryMainDto;

public interface StoryService {

	int createStory(StoryDto storyDto);

	int updateStory(StoryMainDto storyDto);

	int deleteStory(StoryMainDto storyDto);

	List<StoryMainDto> findStoryById(int id);

	String findMemberUsername(int id);

	List<StoryMainDto> findStoryByIdOnly(int writerId);

	List<AttachmentProfileDto> findAttachProf(int id);

	int findIdByUsername(String from);

}
