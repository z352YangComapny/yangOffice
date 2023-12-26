package com.yangworld.app.domain.profile.entity;

import java.util.List;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProfileDetails extends Profile{
	
	private int id;
	private List<Attachment> attachments;
	
	
}