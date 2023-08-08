package com.yangworld.app.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	private int id;
	private String username;
	private String password;
	private String name;
	private Gender gender;
	private String email;
	private String phone;
	private Auth auth;
	private String provider;
	private LocalDateTime regDate;
}