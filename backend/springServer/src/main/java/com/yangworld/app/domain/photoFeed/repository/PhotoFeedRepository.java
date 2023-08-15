package com.yangworld.app.domain.photoFeed.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.photoFeed.entity.PeedDetails;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;

@Mapper
public interface PhotoFeedRepository {

	@Insert("insert into photo_feed (id, writer_id, content) values (seq_attachment_id.nextval, #{writerId}, #{content})")
	int insertPeed(PeedDetails peed);


    @Insert("insert into attachment (id, original_filename, renamed_filename) values (seq_attachment_id.nextval, #{originalFilename}, #{renamedFilename})")
    int insertAttachment(Attachment attach);


    @Select("select * from photo_feed where id = #{name}")
	PhotoFeed selectFeed(String name);

}
