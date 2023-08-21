package com.yangworld.app.domain.story.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yangworld.app.domain.story.dto.StoryDto;
import com.yangworld.app.domain.story.dto.StoryMainDto;

@Mapper
public interface StoryRepository {
	
	@Insert("insert into Story values (seq_story_id.nextval, #{writerId}, #{content}, default)")
	int createStory(StoryDto storyDto);

	@Update("update Story set content = #{content}, reg_date = default where writer_id = #{writerId}")
	int updateStory(StoryDto storyDto);
	
	@Delete("delete from Story where writer_id = #{writerId}")
	int deleteStory(StoryDto storyDto);
	
//	@Select("select * from (select * from story where writer_id = #{id} and reg_date >= (sysdate - 1) union select s.* from story s join follow f on s.writer_id = f.followee where f.follower = #{id} and s.reg_date >= (sysdate - 1)) order by reg_date")
	@Select("select * from (select * from story where writer_id = #{id} union select s.* from story s join follow f on s.writer_id = f.followee where f.follower = #{id}) order by reg_date")
	List<StoryMainDto> findStoryById(int id);
	
	@Select("select username from member where id = #{writerId}")
	String findMemberUsername(String writerId);
	
	@Select("select * from story where writer_id = #{wirterId} order by reg_date")
	List<StoryMainDto> findStoryByIdOnly(int writerId);
	
}
