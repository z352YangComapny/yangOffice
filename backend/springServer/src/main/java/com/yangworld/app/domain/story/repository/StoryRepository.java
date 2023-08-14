package com.yangworld.app.domain.story.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.yangworld.app.domain.story.dto.StoryDto;

@Mapper
public interface StoryRepository {
	
	@Insert("insert into Story values (seq_story_id.nextval, #{writerId}, #{content}, default)")
	int createStory(StoryDto storyDto);

	@Update("update Story set content = #{content}, reg_date = default where id = #{id}")
	int updateStory(StoryDto storyDto);
	
	@Delete("delete from Story where id = #{id}")
	int deleteStory(StoryDto storyDto);

}
