package com.yangworld.app.domain.dm.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.yangworld.app.domain.dm.entity.Dm;

import lombok.Data;

@Data
public class DmSendDto {
	@NotBlank(message = "받는사람 입력")
	private int receiverId;
	
	@NotBlank(message = "dm내용입력")
	private String content;

	public Dm toDm() {
		return Dm.builder()
				.receiverId(receiverId)
				.content(content)
				.build();
	}
}
