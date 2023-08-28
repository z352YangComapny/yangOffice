package com.yangworld.app.domain.story.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payload {
	private PayloadType type;
	private int id;
	private int to;
	private String from;
	private String content;
	private LocalDateTime createdAt;
	
	private String formattedCreatedAt;
	
	private String attach = null;
	
	private int storyFeed = 0;
}
