package com.yangworld.app.domain.dm.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DmListDto {

	private int id;
	private String username; // member
	private String content; // dm
	private int dmRoomId;  // dm
	private int participant1; // dmRoom
	private int participant2; // dmRoom
	private LocalDateTime regDate; // dm
	
	
	
}
