package com.yangworld.app.domain.story.repository;

import org.apache.ibatis.annotations.*;

import com.yangworld.app.domain.story.dto.StoryDto;

@Mapper
public interface StoryRepository {
	
	@Insert("insert into Story values (seq_story_id.nextval, #{writerId}, #{content}, default)")
	int createStory(StoryDto storyDto);

	@Update("update Story set content = #{content}, reg_date = default where id = #{id}")
	int updateStory(StoryDto storyDto);
	
	@Delete("delete from Story where id = #{id}")
	int deleteStory(StoryDto storyDto);

	@Select("select count(*) from Story")
    int getTotalStoryCount();


//	SELECT *
//	FROM (
//	    SELECT *
//	    FROM Story
//	    WHERE writer_id = 1
//	      AND reg_date >= (SYSDATE - 1)
//	    UNION
//	    SELECT s.*
//	    FROM Story s
//	    JOIN follow f ON s.writer_id = f.followee
//	    WHERE f.follower = 1
//	      AND s.reg_date >= (SYSDATE - 1)
//	); 메인에 띄울 스토리를 찾아내는 쿼리
}
