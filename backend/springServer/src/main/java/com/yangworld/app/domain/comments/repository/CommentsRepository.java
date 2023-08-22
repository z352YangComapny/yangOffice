package com.yangworld.app.domain.comments.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yangworld.app.domain.comments.entity.Comments;

@Mapper
public interface CommentsRepository {

	// ok
	@Insert("insert into comments values(seq_comments_id.nextval, #{writerId}, #{comment}, sysdate)")
	int insertComment(@Param(value = "writerId") int writerId,@Param(value = "comment")  String comment);
	// ok
	@Insert("insert into comments_feed (comments_id, photo_feed_id) values(#{writerId}, #{photoFeedId})")
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
	List<Comments> getCommentsByPhotoFeedId(int photoFeedId);







	
}
