package com.yangworld.app.domain.guestbook.dto;

import javax.validation.constraints.NotBlank;

import com.yangworld.app.domain.guestbook.entity.GuestBook;

import lombok.Data;

@Data
public class GuestBookUpdateDto {
	
	private int id;
	private int memberId;
	@NotBlank(message = "수정할 내용을 입력해주세요.")
	private String content;
	
	public GuestBook guestBook() {
		return GuestBook.builder()
				.id(id)
				.memberId(memberId)
				.content(content)
				.build();
	}
	
	
}
