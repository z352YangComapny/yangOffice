package com.yangworld.app.domain.profile.dto;

import javax.validation.constraints.NotBlank;

import com.yangworld.app.domain.profile.entity.Profile;
import com.yangworld.app.domain.profile.entity.State;

import lombok.Data;

@Data
public class ProfileDto {
	
	private int id;
	private int memberId;
	
//	@NotBlank(message = "이거ㅏㄹ뚜세여.")
	private State state;
	private String introduction;
	
	public Profile toProfile() {
		return Profile.builder()
				.state(state)
				.introduction(introduction)
				.build();
	}
	

}
