package com.yangworld.app.domain.photoFeed.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.yangworld.app.domain.attachment.entity.Attachment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PeedCreateDto {
	
	private int writerId;
	private String content;
	private List<Attachment> attachments;
	private LocalDateTime regDate;
	

}
