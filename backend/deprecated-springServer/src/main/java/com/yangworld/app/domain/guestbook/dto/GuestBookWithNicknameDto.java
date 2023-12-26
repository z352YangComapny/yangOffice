package com.yangworld.app.domain.guestbook.dto;

import java.sql.Date;

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
	private int writerId;
	private int memberId;
	private String content;
	private Date regDate;
}
