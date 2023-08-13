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
	private String writerId;
	private String Content;
	private Date regDate;
}
