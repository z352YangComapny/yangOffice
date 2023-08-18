package com.yangworld.app.domain.comments.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comments {
	
	private int id;
	private int writerId;
	private String content;
	private LocalDateTime regDate;
	
}
