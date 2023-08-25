package com.yangworld.app.domain.photoFeed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentPhotoDto {
	
	
	private int attachmentId;
	private int photoFeedId;
}
