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
    Story findStoryOriginById(int storyId);

	@Select("SELECT\n" +
			"    TRUNC(dates.story_date) AS story_date,\n" +
			"    NVL(COUNT(story.reg_date), 0) AS story_count\n" +
			"FROM\n" +
			"    (SELECT TRUNC(SYSDATE) - LEVEL + 1 AS story_date\n" +
			"     FROM dual\n" +
			"     CONNECT BY LEVEL <= 10) dates\n" +
			"        LEFT JOIN\n" +
			"    story ON TRUNC(story.reg_date) = dates.story_date\n" +
			"GROUP BY\n" +
			"    TRUNC(dates.story_date)\n" +
			"ORDER BY\n" +
			"    TRUNC(dates.story_date)")
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

//	@Select("select * from (select * from story where writer_id = #{id} and reg_date >= (sysdate - 1) union select s.* from story s join follow f on s.writer_id = f.followee where f.follower = #{id} and s.reg_date >= (sysdate - 1)) order by reg_date")
	@Select("select * from (select * from story where writer_id = #{id} union select s.* from story s join follow f on s.writer_id = f.followee where f.follower = #{id}) order by reg_date")
	List<StoryDto> findStoryById(int id);
	
	@Select("select nickname from member where id = #{writerId}")
	String findMemberUsername(int writerId);
	
	@Select("select id from member where nickname = #{id}")
	int findIdByUsername(int id);

}
