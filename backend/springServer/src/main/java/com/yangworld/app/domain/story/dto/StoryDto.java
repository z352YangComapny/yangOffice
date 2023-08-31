package com.yangworld.app.domain.story.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StoryDto {
	private int id;
	private String writerId;
	private String content;
	private LocalDateTime regDate;
	
	private String formattedRegDate;
}
