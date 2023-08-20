package com.yangworld.app.domain.dm.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.yangworld.app.domain.dm.entity.Dm;

import lombok.Data;

@Data
public class DmSendDto {
	private int id;
	
	@NotBlank(message = "dm내용입력")
	private String content;

	public Dm toDm() {
		return Dm.builder()
				.content(content)
				.build();
	}
}
