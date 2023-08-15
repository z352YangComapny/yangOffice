package com.yangworld.app.domain.dm.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class DmRoomDto {

	private int id;
	@NotBlank(message ="dm 참여자 입력")
	private List<Integer> participants;
	
	
	
}
