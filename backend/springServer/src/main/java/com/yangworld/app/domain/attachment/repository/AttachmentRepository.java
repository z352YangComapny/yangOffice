package com.yangworld.app.domain.attachment.repository;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.attachment.entity.AttachmentPhotoFeed;
import com.yangworld.app.domain.attachment.entity.AttachmentProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AttachmentRepository {
    List<AttachmentPhotoFeed> findAttachmentByFeedId(int id);

    @Select("SELECT * FROM attachment a JOIN attachment_profile ap ON a.id = ap.attachment_id where profile_id=#{id}")
    AttachmentProfile findAttachmentByProfileId(int id);
}
