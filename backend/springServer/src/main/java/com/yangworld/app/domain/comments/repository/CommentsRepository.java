package com.yangworld.app.domain.comments.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentsRepository {


	@Insert("insert into comments values(seq_comments_id.nextval, #{writerId}, #{content}, sysdate)")
	int insertComment(@Param(value = "writerId") int writerId,@Param(value = "content")  String content);

//	@Insert("insert into comments_feed (comments_id, photo_feed_id) values(seq_comments_id.currval, seq_photo_feed_id.currval)")
	@Insert("insert into comments_feed (comments_id) values(seq_comments_id.currval)")
	int insertCommnetFeed();
	




	
}
