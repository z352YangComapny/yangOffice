package com.yangworld.app.domain.guestbook.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestBook {
	
	private int id;
	private int writerId;
	private int memberId;
	private String content;
	private LocalDateTime regDate;
	

}
