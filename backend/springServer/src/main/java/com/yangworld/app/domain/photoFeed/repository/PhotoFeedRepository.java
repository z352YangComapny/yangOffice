package com.yangworld.app.domain.photoFeed.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.photoFeed.dto.AttachmentPhotoDto;
import com.yangworld.app.domain.photoFeed.dto.FeedCreateDto;
import com.yangworld.app.domain.photoFeed.dto.PhotoAttachmentFeedDto;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;

@Mapper
public interface PhotoFeedRepository {

	@Insert("insert into photo_feed (id, writer_id, content) values (seq_photo_feed_id.nextval, #{writerId}, #{content})")
	int insertFeed(FeedDetails peed);


    @Insert("insert into attachment (id, original_filename, renamed_filename) values (seq_attachment_id.nextval, #{originalFilename}, #{renamedFilename})")
    int insertAttachment(Attachment attach);
    
    @Insert("insert into attachment_photo_feed (attachment_id, photo_feed_id) values (seq_attachment_id.currval, seq_photo_feed_id.currval)")
    int insertLink();

    @Select("select * from photo_feed where writer_id = #{writerId}") // query문 다시 짜야됨 nickname을 photo_feed랑 join하기
    List<PhotoAttachmentFeedDto> selectFeed(int writerId);

    @Select("select * from attachment_photo_feed where photo_feed_id = #{photoFeedId}") // query문 다시 짜야됨 nickname을 photo_feed랑 join하기
    AttachmentPhotoDto selectAttachmentPhoto(int photoFeedId);
    
    @Delete("delete from photo_feed where id = #{id}")
	int deleteFeed(FeedCreateDto feed);

    

	@Select("select * from attachment where id = #{id}")
	List<Attachment> selectAttachment(int id);


    
    
}
