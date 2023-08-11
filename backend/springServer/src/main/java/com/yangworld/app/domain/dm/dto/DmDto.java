package com.yangworld.app.domain.dm.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class DmDto {
	private int id;
	@NotBlank(message = "받는사람 입력")
	private int receiverId;
	@NotBlank(message = "보내는사람 입력")
	private int senderId;
	
	@NotBlank(message = "dm내용입력")
	private String content;
}
