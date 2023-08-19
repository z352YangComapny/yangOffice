package com.yangworld.app.domain.attachment.repository;

import com.yangworld.app.domain.attachment.entity.AttachmentPhotoFeed;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttachmentRepository {
    List<AttachmentPhotoFeed> findAttachmentByFeedId(int id);
}
