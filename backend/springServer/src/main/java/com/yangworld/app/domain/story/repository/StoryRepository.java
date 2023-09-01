package com.yangworld.app.domain.story.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.story.dto.AttachmentProfileDto;
import com.yangworld.app.domain.story.dto.StoryDto;
import com.yangworld.app.domain.story.dto.StoryMainDto;

@Mapper
public interface StoryRepository {
	
	@Insert("insert into story values (seq_story_id.nextval, #{writerId}, #{content}, default)")
	int createStory(StoryDto storyDto);

	@Update("update story set content = #{content}, reg_date = default where id = #{id}")
	int updateStory(StoryMainDto storyDto);
	
	@Delete("delete from story where id = #{id}")
	int deleteStory(StoryMainDto storyDto);
	
//	@Select("select * from (select * from story where writer_id = #{id} and reg_date >= (sysdate - 1) union select s.* from story s join follow f on s.writer_id = f.followee where f.follower = #{id} and s.reg_date >= (sysdate - 1)) order by reg_date")
	@Select("select * from (select * from story where writer_id = #{id} union select s.* from story s join follow f on s.writer_id = f.followee where f.follower = #{id}) order by reg_date")
	List<StoryMainDto> findStoryById(int id);
	
	@Select("select nickname from member where id = #{writerId}")
	String findMemberUsername(int writerId);
	
	@Select("select * from story where writer_id = #{wirterId} order by reg_date")
	List<StoryMainDto> findStoryByIdOnly(int writerId);

	@Select("select renamed_filename from attachment where id = (select attachment_id from attachment_profile where profile_id = 1)")
	String findAttach(int id);

	List<AttachmentProfileDto> findAttachProf(int id);
	
	@Select("select id from member where nickname = #{from}")
	int findIdByUsername(String from);

	@Select("select feed_id from story_feed where story_id = #{id}")
	int findStoryFeedByStoryId(int id);
	
}
