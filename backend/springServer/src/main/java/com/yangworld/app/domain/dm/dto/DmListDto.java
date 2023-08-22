package com.yangworld.app.domain.dm.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DmListDto {

	private String username; // member
	private String content; // dm
	private int dmRoomId;  // dm
	private int participant1; // dmRoom
	private int participant2; // dmRoom
	private LocalDateTime regDate; // dm
	

	
	
}
