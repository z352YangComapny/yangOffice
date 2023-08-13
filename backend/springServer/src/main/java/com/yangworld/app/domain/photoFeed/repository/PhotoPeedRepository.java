package com.yangworld.app.domain.photoFeed.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.photoFeed.entity.PeedDetails;

@Mapper
public interface PhotoPeedRepository {

	@Insert("insert into photo_peed values(seq_attachment_id.nextval, #{writerId}, #{content}, default")
	int insertPeed(PeedDetails peed);

	@Insert("insert into attachment values(seq_attachment_id.nextval, #{originalFilename}, #{renamedFilename}, default)")
	int insertAttachment(Attachment attach);

}
