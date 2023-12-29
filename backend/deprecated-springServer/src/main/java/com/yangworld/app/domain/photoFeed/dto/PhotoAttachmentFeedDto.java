package com.yangworld.app.domain.photoFeed.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.photoFeed.entity.Like;
import com.yangworld.app.domain.question.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoAttachmentFeedDto {
	
	private int id;
	private int writerId;
	private String content;
	private int commentCount;
	private int attachmentId;
	private int photoFeedId;
	private int likeCount;
	private String originalFilename;
	private String renamedFilename;
	private List<AttachmentPhotoDto> attachmentPhotoDto;
	private List<Comment> comments;
	private List<Like> like;
	private List<Attachment> attachments;
	private LocalDateTime regDate;
	
	
	
}
