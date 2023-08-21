package com.yangworld.app.domain.profile.service;

import java.util.List;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.profile.dto.ProfileDto;
import com.yangworld.app.domain.profile.entity.ProfileDetails;

public interface ProfileService {


//	int insertProfile(ProfileDto profile);

//	int updateProfile(ProfileDto profile);

	int insertProfile(ProfileDetails profile);
	
	int resetProfile(int memberId);

	int updateProfile(ProfileDetails profile);

	ProfileDetails getProfileByMemberId(int id);

	List<Attachment> getAttachmentsByProfileId(int profileId);


//	int resetProfile(ProfileDto profile);


	
	
	
}