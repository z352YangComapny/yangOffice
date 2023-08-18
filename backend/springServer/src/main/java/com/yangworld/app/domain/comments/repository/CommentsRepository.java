package com.yangworld.app.domain.comments.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.yangworld.app.domain.comments.entity.Comments;

@Mapper
public interface CommentsRepository {


	@Insert("insert into comments values(seq_comments_id.nextval, #{writerId}, #{content}, sysdate)")
	int insertComment(@Param(value = "writerId") int writerId,@Param(value = "content")  String content);
	
	// commentLinkTable insert
	@Insert("insert into comments_feed (comments_id, photo_feed_id) values(#{commentId}, #{photoFeedId})")
	int insertCommentFeed(@Param(value = "commentId")int commentId, @Param(value = "photoFeedId")int photoFeedId);
	
	// comment update
	@Update("update comments set content = #{content} where id = #{commentId}")
	int updateComment(@Param("commentId") int commentId, @Param("content") String newContent);
	
	@Delete("delete from comments where id = #{commentId}")
	int deleteComment(@Param("commentId") int commentId);

	@Delete("delete from comments_feed WHERE comments_id = #{commentId}")
	int deleteCommentFeed(@Param("commentId") int commentId);


	List<Comments> getCommentsByPhotoFeedId(int photoFeedId);
	





	
}
