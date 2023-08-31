package com.yangworld.app.domain.story.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payload {
	private PayloadType type;
	private int id;
	private String writerId;
	private String content;
	private LocalDateTime createdAt;
	
	private String formattedCreatedAt;
}
