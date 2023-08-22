package com.yangworld.app.domain.dm.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DmDetailsDto {

	private int id;
	private String name;
	private String renamedFileName; // attahment
	private int receiverId;
	private int senderId;
	private String content;
	private int dmRoomId;
	private LocalDateTime regDate;
	
}
