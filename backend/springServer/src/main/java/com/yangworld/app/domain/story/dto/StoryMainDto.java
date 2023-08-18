package com.yangworld.app.domain.story.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class StoryMainDto {
	private int id;
	private String writerId;
	private String content;
	private Date regDate;
}
