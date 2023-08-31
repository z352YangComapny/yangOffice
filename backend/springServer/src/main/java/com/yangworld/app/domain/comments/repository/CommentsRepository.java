package com.yangworld.app.domain.comments.repository;

import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.photoFeed.entity.CommentFeed;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentsRepository {

    @Select("select * from comments_feed where photo_feed_id = #{photoFeedId}")
    public List<CommentFeed> getCommentsByPhotoFeedId(int feedId);

    @Select("select * from comments where id = #{commentId}")
    List<Comments> commentByPhotoFeedId(int commentId);

    @Select("select * from comments cm join member mem on cm.writer_id = mem.id where cm.id = #{commentId}")
    Comments nickNameByCommentsId(int id);
}
