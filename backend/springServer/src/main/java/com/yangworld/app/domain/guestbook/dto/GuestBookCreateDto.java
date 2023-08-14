package com.yangworld.app.domain.guestbook.dto;

import javax.validation.constraints.NotBlank;

import com.yangworld.app.domain.guestbook.entity.GuestBook;

import lombok.Data;

@Data
public class GuestBookCreateDto {
	
	private int memberId;
	@NotBlank(message = "내용을 입력해주세요.")
	private String content;
	
	public GuestBook guestBook() {
		return GuestBook.builder()
				.memberId(memberId)
				.content(content)
				.build();
		
	}
	

}
