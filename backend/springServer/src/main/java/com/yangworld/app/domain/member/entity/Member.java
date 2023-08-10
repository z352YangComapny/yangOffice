package com.yangworld.app.domain.member.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	private int id;
	private String username;
	private String name;
	private String password;
	private String nickname;
	private Date birthday;
	private Gender gender;
	private String phone;
	private String email;
	private List<SimpleGrantedAuthority> authorities;
	private String provider;
	private LocalDateTime regDate;
}