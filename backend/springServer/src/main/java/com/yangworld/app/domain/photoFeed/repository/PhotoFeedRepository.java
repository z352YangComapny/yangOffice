package com.yangworld.app.domain.photoFeed.repository;

import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.photoFeed.dto.AttachmentPhotoDto;
import com.yangworld.app.domain.photoFeed.dto.Like;
import com.yangworld.app.domain.photoFeed.dto.PhotoFeedAll;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;
import org.apache.ibatis.annotations.*;
import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface PhotoFeedRepository {

    // feedInsert
    @Insert("insert into photo_feed (id, writer_id, content) values (seq_photo_feed_id.nextval, #{writerId}, #{content})")
    int insertFeed(FeedDetails feed);

    @Insert("insert into attachment (id, original_filename, renamed_filename) values (seq_attachment_id.nextval, #{originalFilename}, #{renamedFilename})")
    int insertAttachment(Attachment attach);
    @Insert("insert into attachment_photo_feed (attachment_id, photo_feed_id) values (seq_attachment_id.currval, seq_photo_feed_id.currval)")
    int insertLink();
    // feedInsert
    @Select("select count(*) from photo_feed")
    int getPhotoFeedTotalCount();
    @Select("select * from photo_feed order by id DESC")
    List<PhotoFeed> getPhotoRawFeed(RowBounds rowBounds);


    @Select("select * from photo_feed where id = #{feedId}")
    List<PhotoFeedAll> findFeedById(int feedId);
    @Select("select * from photo_feed where writer_id = #{id}")
    List<PhotoFeedAll> findAllFeedByWriterId(int id);

    @Select("select * from attachment_photo_feed where photo_feed_id = #{photoFeedId}")
    List<AttachmentPhotoDto> findAttachmentPhotoFeedByPhotoFeedId(int photoFeedId);
    @Select("select * from attachment where id = #{id}")
    List<Attachment> findAttachmentsById(int attachmentId);
    @Select("select count(*) from likes where photo_feed_id = #{id}")
    int getLikeCount(int feedId);

    @Select("select count(*) as comment_count from comments_feed where photo_feed_id = #{id}")
    int getCommentCount(int feedId);

    @Delete("delete from photo_feed where id = #{feedId}")
    int deleteFeed(int feedId);

    @Delete("delete from attachment where id = #{attachmentId}")
    int deleteAttachment(int attachmentId);

    @Delete("delete from attachment_photo_feed where photo_feed_id = #{feedId}")
    int deleteLink(int feedId);

    @Select("SELECT * FROM photo_feed WHERE writer_id = #{mebmerId} AND ROWNUM <= 1")
    PhotoFeed findById(int memberId);

    @Update("update photo_feed set content = #{content} where id = #{feedId}")
    int updateFeed(@Param("feedId") int feedId, @Param("content") String content);


    @Insert("insert into likes (photo_feed_id, member_id) values(#{photoFeedId}, #{memberId})")
    int insertLike(@Param(value = "photoFeedId") int photoFeedId, @Param(value = "memberId") int memberId);

    @Delete("delete from likes where photo_feed_id = #{photoFeedId} and member_id = #{memberId}")
    int deleteLike(@Param(value = "photoFeedId") int photoFeedId, @Param(value = "memberId") int memberId);

    @Select("select * from likes where photo_feed_id = #{feedId} and member_id = #{memberId}")
    Like likeCheck(@Param("feedId") int feedId,@Param("memberId") int memberId);

    @Select("select * from member where userName = #{userName}")
    Member findByuserName(String userName);
}
