package com.yangworld.app.domain.profile.entity;

import java.util.List;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.photoFeed.entity.PeedDetails;
import com.yangworld.app.domain.photoFeed.entity.PhotoPeed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Profile {

	private int id;
	private int memberId;
	private State state;
	private String introduction;
}

