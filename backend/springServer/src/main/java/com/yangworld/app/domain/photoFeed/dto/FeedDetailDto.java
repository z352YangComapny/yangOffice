package com.yangworld.app.domain.photoFeed.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.yangworld.app.domain.attachment.entity.Attachment;

public class FeedDetailDto {
	
	private int id;
	private int writerId;
	private String content;
	private List<Attachment> attachments;
	private LocalDateTime regDate;


}
