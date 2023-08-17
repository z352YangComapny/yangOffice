package com.yangworld.app.domain.attachment.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
	
	private int id;
	private String originalFilename;
	private String renamedFilename;
	private LocalDateTime regDate;
}
