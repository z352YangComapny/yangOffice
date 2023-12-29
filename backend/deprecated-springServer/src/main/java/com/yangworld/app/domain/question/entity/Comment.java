package com.yangworld.app.domain.question.entity;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

	private int id;
	private int writerId;
	private String content;
	private LocalDateTime regDate;
	
}
