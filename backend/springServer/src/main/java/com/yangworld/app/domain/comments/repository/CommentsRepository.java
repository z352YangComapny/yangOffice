package com.yangworld.app.domain.comments.repository;

import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.photoFeed.dto.Comment;
import com.yangworld.app.domain.photoFeed.entity.CommentFeed;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CommentsRepository {

    @Select("select * from comments_feed where photo_feed_id = #{photoFeedId}")
    List<CommentFeed> getCommentsByPhotoFeedId(int feedId);

    @Select("select * from comments where id = #{commentId}")
    List<Comments> commentByPhotoFeedId(int commentId);

    @Select("select * from comments cm join member mem on cm.writer_id = mem.id where cm.id = #{commentId}")
    Comments nickNameByCommentsId(int id);

    @Select("select * from comments where id = #{commentId}")
    Comment commentByCommentId(int commentId);

    @Update("update comments set content = #{content}, reg_date = sysdate where id = #{commentId}")
    int commentUpdate(@Param(value ="commentId")int commentId, @Param("content") String content);
}
