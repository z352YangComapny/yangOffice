package com.yangworld.app.domain.chat;

import javax.validation.constraints.NotBlank;

import com.yangworld.app.domain.chat.entity.Chat;

import lombok.Data;

@Data
public class SendChatDto {

	private int id;
	
	@NotBlank(message = "채팅 내용 입력")
	private String chatContent;
	
	public Chat toChat() {
		return Chat.builder()
				.chatContent(chatContent)
				.build();
	}
}
