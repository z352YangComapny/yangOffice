package com.yangworld.app.domain.comments.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yangworld.app.domain.comments.dto.CommentAllDto;
import com.yangworld.app.domain.comments.entity.CommentFeed;
import com.yangworld.app.domain.comments.entity.Comments;

@Mapper
public interface CommentsRepository {

	// ok
	@Insert("insert into comments values(seq_comments_id.nextval, #{writerId}, #{content}, sysdate)")
//	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insertComment(@Param(value = "writerId") int writerId, @Param(value = "content") String content);
	
	// ok
	@Insert("insert into comments_feed (comments_id, photo_feed_id) values(seq_comments_id.currval, #{photoFeedId})")
	int insertCommentFeed(@Param(value ="writerId") int writerId, @Param(value ="photoFeedId") int photoFeedId);
	
	// ok
	@Update("update comments set content = #{content} where id = #{commentId}")
	int updateComment(@Param(value ="commentId") int commentId, @Param(value ="content") String newContent);
	
	// ok
	@Delete("delete from comments where id = #{commentId}")
	int deleteComment(@Param(value ="commentId") int commentId);

	// ok
	@Delete("delete from comments_feed where comments_id = #{commentId}")
	int deleteCommentFeed(@Param(value ="commentId") int commentId);

	
	// ok
	@Select("select c.id, c.writer_id, c.content, c.reg_date from comments_feed cf join comments c on cf.comments_id = c.id where cf.photo_feed_id = #{photoFeedId}")
	List<CommentFeed> getCommentsByPhotoFeedId(int photoFeedId);
	
	@Select("select * from comments where id = #{commentId}")
	List<Comments> commentByPhotoFeedId(int commentId);
	
	//---------------------------------------------------------------------------------------------------------
	
	@Insert("insert into comments_question (comments_id, question_id) values (seq_comments_id.currval, #{questionId})")
	int insertCommentQna(@Param(value = "commentId") int commentId, @Param(value = "questionId") int questionId);

	@Select("select * from comments_question cq join comments c on cq.comments_id = c.id where cq.question_id = #{questionId}")
	List<Comments> getCommentsByQuestionId(int questionId);

	@Update("update comments c set c.content = #{content} where c.id in (select cq.comments_id from comments_question cq where cq.question_id = #{questionId})")
	int updateCommentQna(@Param("questionId")int questionId,@Param("content") String content);

	@Delete("delete comments where id = #{id}")
	int deleteCommentQna(int commentId);


	
	





	
}
