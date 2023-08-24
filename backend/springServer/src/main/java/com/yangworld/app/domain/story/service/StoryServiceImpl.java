package com.yangworld.app.domain.story.service;

import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.repository.MemberRepository;
import com.yangworld.app.domain.story.dto.StoryAdminDto;
import com.yangworld.app.domain.story.entity.Story;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yangworld.app.domain.story.dto.StoryDto;
import com.yangworld.app.domain.story.repository.StoryRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class  StoryServiceImpl implements StoryService{

	@Autowired
	private StoryRepository storyRepository;
	@Autowired
	private MemberRepository memberRepository;

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

	@Override
	public int getTotalStoryCount() {
		return storyRepository.getTotalStoryCount();
	}

	@Override
	@Transactional
	public List<StoryAdminDto> getAdminStory(int pageNo, int pageSize) {
		int offset = (pageNo-1)*pageSize;
		RowBounds rowBounds = new RowBounds(offset, pageSize);
		List<StoryAdminDto> result = new ArrayList<>();
		List<Story> _result = storyRepository.getAdminStory(rowBounds);
		for(Story story : _result){
			Member username = memberRepository.findById(Integer.parseInt(story.getWriterId()));
			StoryAdminDto storyAdminDto = StoryAdminDto.builder()
					.id(story.getId())
					.username(username.getUsername())
					.content(story.getContent())
					.regDate(story.getRegDate())
					.build();

			result.add(storyAdminDto);
		}
		return result;
	}
}
