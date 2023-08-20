package com.yangworld.app.domain.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.profile.dto.ProfileDto;
import com.yangworld.app.domain.profile.entity.AttachmentProfile;
import com.yangworld.app.domain.profile.entity.Profile;
import com.yangworld.app.domain.profile.entity.ProfileDetails;
import com.yangworld.app.domain.profile.repository.ProfileRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	

//	@Override
//	public int insertProfile(ProfileDto profile) {
//		return profileRepository.insertProfile(profile);
//	}

//	@Override
//	public int updateProfile(ProfileDto profile) {
//		return profileRepository.updateProfile(profile);
//	}
	
	@Override
	@Transactional
	public int insertProfile(ProfileDetails profile) {
		int result = 0;
		
		result = profileRepository.insertProfile(profile); 
		
		List<Attachment> attachments = ((ProfileDetails) profile).getAttachments();
		if(attachments != null && !attachments.isEmpty()) {
			for(Attachment attach : attachments) {
				attach.setId(profile.getId());
				result = profileRepository.insertAttachment(attach);
				log.info("profileId={}", profile.getId());
	            log.info("attach = {}", attach);
				
				// attachment_profile 테이블에 관련 정보 추가
				//AttachmentProfile attachmentProfile = new AttachmentProfile();
	           int attachId = attach.getId();
	           int profileId = profile.getId();
	           log.info("attachId222 ={}", attachId);
	           log.info("prfile222={}", profileId);
				//attachmentProfile.setAttachmentId(attach.getId());
				//attachmentProfile.setProfileId(profile.getId())
				result = profileRepository.insertAttachmentProfile(attachId, profileId);
			}
		}
		return result;
	}
	
	@Override
	@Transactional
	public int updateProfile(ProfileDetails profile) {
	    int result = 0;

	    result = profileRepository.updateProfile(profile);
	    log.info("profileId={}", profile.getId());

	    List<Attachment> attachments = ((ProfileDetails) profile).getAttachments();
	    if (attachments != null && !attachments.isEmpty()) {
	        for (Attachment attach : attachments) {
	            attach.setId(profile.getId());
	            result = profileRepository.updateAttachment(attach);
	            log.info("profileId={}", profile.getId());
	            log.info("attach = {}", attach);
	            
	            AttachmentProfile attachmentProfile = new AttachmentProfile();
	            attachmentProfile.setAttachmentId(attach.getId());
	            attachmentProfile.setProfileId(profile.getId());
	            result = profileRepository.updateAttachmentProfile(attachmentProfile);
	            log.info("attachmentProfile = {}",attachmentProfile);
	        }
	    }
	    return result;
	}
	
	@Override
    public ProfileDetails getProfileByMemberId(int id) {
        return profileRepository.getProfileByMemberId(id);
    }
	@Override
	public int resetProfile(int memberId) {
		return profileRepository.resetProfile(memberId);
		
	}

//	@Override
//	public int resetProfile(ProfileDto profile) {
//		return profileRepository.resetProfile(profile);
//	}
	
}