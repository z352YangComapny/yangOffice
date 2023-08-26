package com.yangworld.app.domain.chat.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatListDto {

	private int id;
	private int memberId;
	@NotBlank(message = "채팅 내용 입력.")
	private String content;
	private LocalDateTime sendDate;
}
