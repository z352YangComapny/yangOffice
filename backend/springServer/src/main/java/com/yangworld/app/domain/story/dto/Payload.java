package com.yangworld.app.domain.story.dto;

import java.sql.Date;
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
	private int to;
	private String from;
	private String content;
	private LocalDateTime createdAt;
	
	private String attach;
}
