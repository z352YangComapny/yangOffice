package com.yangworld.app.domain.story.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Story {
	private int id;
	private int writerId;
	private String content;
	private Date regDate;
}
