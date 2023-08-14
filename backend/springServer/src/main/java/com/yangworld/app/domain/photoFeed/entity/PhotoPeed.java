package com.yangworld.app.domain.photoFeed.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoPeed {
	
	private int id;
	private int writerId;
	private String content;
	private LocalDateTime regDate;

}
