package com.yangworld.app.domain.profile.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

	private int id;
	private int memberId;
	private State state;
	private String introduction;
}

