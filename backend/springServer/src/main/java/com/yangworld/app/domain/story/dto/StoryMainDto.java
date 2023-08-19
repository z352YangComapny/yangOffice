package com.yangworld.app.domain.story.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryMainDto {
	private int id;
	private String writerId;
	private String content;
	private Date regDate;
}
