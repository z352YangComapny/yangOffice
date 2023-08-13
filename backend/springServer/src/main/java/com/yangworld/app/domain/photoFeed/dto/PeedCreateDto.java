package com.yangworld.app.domain.photoFeed.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeedCreateDto {
	
	private int id;
	private int writerId;
	private String content;
	private LocalDateTime regDate;
	

}
