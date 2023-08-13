package com.yangworld.app.domain.photoFeed.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.photoFeed.entity.PeedDetails;

@Mapper
public interface PhotoPeedRepository {

	@Insert("insert into photo_feed (id, writer_id, content) values (seq_attachment_id.nextval, #{writerId}, #{content})")
	int insertPeed(PeedDetails peed);


    @Insert("insert into attachment (id, original_filename, renamed_filename) values (seq_attachment_id.nextval, #{originalFilename}, #{renamedFilename})")
    int insertAttachment(Attachment attach);

}
