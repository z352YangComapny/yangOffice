package com.yangworld.app.domain.profile.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentProfileDto {
	
	private int attachmentId;
    private int profileId;
}
