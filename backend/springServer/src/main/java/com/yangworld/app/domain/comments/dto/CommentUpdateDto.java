package com.yangworld.app.domain.comments.dto;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import lombok.Builder;

import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdateDto {
	
	private int commentId;
	private int photoFeedId;
	private String newContent;
	

}
