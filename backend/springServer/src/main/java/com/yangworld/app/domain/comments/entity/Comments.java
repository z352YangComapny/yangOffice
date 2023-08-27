package com.yangworld.app.domain.comments.entity;

import java.time.LocalDateTime;

import com.yangworld.app.domain.profile.entity.Profile;
import com.yangworld.app.domain.profile.entity.State;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Comments {
	
	private int id;
	private int writerId;
	private String nickName;
	private String content;
	private LocalDateTime regDate;
	
}
