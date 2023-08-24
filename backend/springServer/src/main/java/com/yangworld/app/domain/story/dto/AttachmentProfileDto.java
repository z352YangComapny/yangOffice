package com.yangworld.app.domain.story.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttachmentProfileDto {
	private int profileId;
	private String originalFilename;
	private String renamedFilename;
}
