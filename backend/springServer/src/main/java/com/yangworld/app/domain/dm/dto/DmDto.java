package com.yangworld.app.domain.dm.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class DmDto {
	private int id;
	private int receiverId;
	private int senderId;
	
	@NotBlank(message = "dm내용입력")
	private String content;
}
