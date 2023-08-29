package com.yangworld.app.domain.guestbook.dto;

import java.time.LocalDateTime;

import com.yangworld.app.domain.guestbook.entity.GuestBook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestBookWithNicknameDto {
	private int id; // guestbookId
	private String nickname;
	private int writerId;
	private int memberId;
	private String content;
	private LocalDateTime regDate;
	
	
	public GuestBookWithNicknameDto guestBook() {
		return GuestBookWithNicknameDto.builder()
				.id(id)
				.nickname(nickname)
				.writerId(writerId)
				.memberId(memberId)
				.content(content)
				.build();
	}

}
