package com.yangworld.app.domain.story.repository;

import com.yangworld.app.domain.story.dto.StoryDailyDto;
import com.yangworld.app.domain.story.entity.Story;
import org.apache.ibatis.annotations.*;

import com.yangworld.app.domain.story.dto.StoryDto;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

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
	@Select("select * from story order by reg_date desc")
	List<Story> getAdminStory(RowBounds rowBounds);

	@Select("select * from story where id = #{storyId}")
    Story findStoryById(int storyId);

	@Select("SELECT\n" +
			"    TRUNC(reg_date) AS story_date,\n" +
			"    COUNT(*) AS story_count\n" +
			"FROM\n" +
			"    story\n" +
			"WHERE\n" +
			"        reg_date > TRUNC(SYSDATE) - 10\n" +
			"  AND reg_date <= TRUNC(SYSDATE)\n" +
			"GROUP BY\n" +
			"    TRUNC(reg_date)\n" +
			"ORDER BY\n" +
			"    TRUNC(reg_date)")
	List<StoryDailyDto> findStoryDaily();


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
