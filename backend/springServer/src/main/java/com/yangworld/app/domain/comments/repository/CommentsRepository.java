package com.yangworld.app.domain.comments.repository;

import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.photoFeed.dto.Comment;
import com.yangworld.app.domain.photoFeed.entity.CommentFeed;
import org.apache.ibatis.annotations.*;

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
    @Delete("delete from comments where id = #{commentId}")
    int commentDelete(int commentId);

    @Delete("delete from comments_feed where comments_id = #{commentId}")
    int commentFeedDelete(int commentId);
    @Insert("insert into comments values(seq_comments_id.nextval, #{writerId}, #{content}, sysdate)")
    int commentsCreate(@Param("writerId") int writerId,@Param("content") String content);

    @Select("select * from (select * from comments where writer_id = #{writerId} and content = #{content} order by id desc) where rownum = 1")
    Comments commentsIdByWriterIdContent(@Param("writerId") int writerId, @Param("content") String content);

    @Insert("insert into comments_feed (comments_id, photo_feed_id) values(#{id}, #{feedId})")
    int commentFeedCreate(@Param("id") int id, @Param("feedId") int feedId);
}
