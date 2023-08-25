package com.yangworld.app.domain.chat.dto;

import javax.validation.constraints.NotBlank;

import com.yangworld.app.domain.chat.entity.Chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendChatDto {

	private int id;
	
	@NotBlank(message = "채팅 내용 입력.")
	private String content;
	
	public Chat toChat() {
		return Chat.builder()
				.chatContent(content)
				.build();
			
	}
}
