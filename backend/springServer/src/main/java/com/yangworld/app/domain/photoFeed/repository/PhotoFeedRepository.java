package com.yangworld.app.domain.photoFeed.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.photoFeed.dto.FeedCreateDto;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;

@Mapper
public interface PhotoFeedRepository {

	@Insert("insert into photo_feed (id, writer_id, content) values (seq_photo_feed_id.nextval, #{writerId}, #{content})")
	int insertFeed(FeedDetails peed);


    @Insert("insert into attachment (id, original_filename, renamed_filename) values (seq_attachment_id.nextval, #{originalFilename}, #{renamedFilename})")
    int insertAttachment(Attachment attach);

    @Insert("insert into attahment_photo_feed values()")
    int insertLink();

    @Select("select * from member where nickname = #{nickName}") // query문 다시 짜야됨 nickname을 photo_feed랑 join하기
	PhotoFeed selectFeed(String nickName);

    
    @Delete("delete from photo_feed where id = #{id}")
	int deleteFeed(FeedCreateDto feed);


    
    
}
