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
import com.yangworld.app.domain.member.entity.Member;

@Mapper
public interface CommentsRepository {

	// ok
	@Insert("insert into comments values(seq_comments_id.nextval, #{writerId}, #{comment}, sysdate)")
	int insertComment(@Param(value = "writerId") int writerId,@Param(value = "comment")  String comment);
	
	// ok
	@Insert("insert into comments_feed (comments_id, photo_feed_id) values(seq_comments_id.currval, #{photoFeedId})")
	int insertCommentFeed(@Param(value ="writerId") int writerId, @Param(value ="photoFeedId") int photoFeedId);

	// ok
	@Update("update comments set content = #{newContent} where id = #{commentId}")
	int updateComment(@Param(value ="commentId") int commentId, @Param(value ="newContent") String newContent);

	// ok
	@Delete("delete from comments where id = #{commentId}")
	int deleteComment(@Param(value ="commentId") int commentId);
	// ok
	@Delete("delete from comments_feed where comments_id = #{commentId}")
	int deleteCommentFeed(@Param(value ="commentId") int commentId);
	
	@Select("select * from comments_feed where photo_feed_id = #{photoFeedId}")
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

	@Select("select * from comments cm join member mem on cm.writer_id = mem.id where cm.id = #{commentId}")
	Comments nickNameByCommentsId(int commentId);



	
	





	
}
