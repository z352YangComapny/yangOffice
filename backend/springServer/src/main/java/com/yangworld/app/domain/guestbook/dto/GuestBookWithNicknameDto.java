package com.yangworld.app.domain.guestbook.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestBookWithNicknameDto {
	private int id;
	private String nickname;
	private int memberId;
	private String content;
	private LocalDateTime regDate;	

}
