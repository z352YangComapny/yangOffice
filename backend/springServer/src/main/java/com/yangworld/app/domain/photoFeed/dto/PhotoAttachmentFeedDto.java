package com.yangworld.app.domain.photoFeed.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.photoFeed.entity.Like;

import lombok.Data;

@Data
public class PhotoAttachmentFeedDto {
	
	private int id;
	private int writerId;
	private String content;
	private List<Like> like;
	private List<AttachmentPhotoDto> attachmentPhotoDto;
	private List<Attachment> attachments;
	private LocalDateTime regDate;
	
}
