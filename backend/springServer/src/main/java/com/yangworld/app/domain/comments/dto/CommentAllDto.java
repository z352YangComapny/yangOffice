package com.yangworld.app.domain.comments.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentAllDto {

	private int id;
	private int writerId;
	private String content;
	private LocalDateTime regDate;
	
}
