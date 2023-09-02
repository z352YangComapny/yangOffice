package com.yangworld.app.domain.profile.service;

import com.yangworld.app.commons.HelloSpringUtils;
import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.attachment.entity.AttachmentProfile;
import com.yangworld.app.domain.attachment.repository.AttachmentRepository;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.repository.MemberRepository;
import com.yangworld.app.domain.photoFeed.dto.AttachmentDto;
import com.yangworld.app.domain.profile.dto.AdminProfileDto;
import com.yangworld.app.domain.profile.dto.AttachmentProfileDto;
import com.yangworld.app.domain.profile.dto.ProfileAll;
import com.yangworld.app.domain.profile.entity.Profile;
import com.yangworld.app.domain.profile.entity.ProfileDetails;
import com.yangworld.app.domain.profile.entity.State;
import com.yangworld.app.domain.profile.repository.ProfileRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ProfileServiceImpl implements ProfileService {


	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	AttachmentRepository attachmentRepository;
	@Autowired
	MemberRepository memberRepository;


	@Override
	public AdminProfileDto findProfileByMemberIdForAdmin(int id) {
		Profile profile = profileRepository.findProfileByMemberIdForAdmin(id);
		AttachmentProfile attachment = attachmentRepository.findAttachmentByProfileId(profile.getId());
		Member member = memberRepository.findById(id);
		int followerCount = memberRepository.findFollowerCountByMemberId(id);
		int followeeCount = memberRepository.findFolloweeCountByMemberId(id);

		//        private int id;
		//        private int memberId;
		//        private State state;
		//        private String introduction;
		//        private String renamedFilename;
		//        private int follower;
		//        private int followee;

		AdminProfileDto adminProfileDto = AdminProfileDto.builder()
				.id(profile.getId())
				.username(member.getUsername())
				.state(profile.getState())
				.introduction(profile.getIntroduction())
				.renamedFilename(attachment.getRenamedFilename())
				.follower(followerCount)
				.followee(followeeCount)
				.build();
		log.info("{}",adminProfileDto);
		return adminProfileDto;
	}


	@Override
	public List<ProfileAll> findProfileAll(String userName) {

		Member _userName = profileRepository.findByUsername(userName);

		int memberId = _userName.getId();

		List<ProfileAll> profileDetails = profileRepository.findAllProfileByMemberId(memberId);

		for(ProfileAll profileAll : profileDetails) {

			int profileId = profileAll.getId();

			List<AttachmentProfileDto> attachmentProfile = profileRepository.findAttachmentProfileByProfileId(profileId);

			profileAll.setAttachments(new ArrayList<>());

			for(AttachmentProfileDto attachments : attachmentProfile) {
				int attachmentId = attachments.getAttachmentId();
				List<Attachment> attachment = profileRepository.findAttachmentsById(attachmentId);
				for(Attachment att : attachment) {
					if(att != null) {
						AttachmentDto attachmentDto = new AttachmentDto();
						attachmentDto.setRenamedFilename(att.getRenamedFilename());

						profileAll.getAttachments().add(attachmentDto);
					}
				}
			}

		}


		return profileDetails;
	}



	@Override
	public int updateProfile(int profileId, State state, String introduction, PrincipalDetails member,
			List<MultipartFile> upFiles) throws IllegalStateException, IOException {

		int result = 0;

		List<Attachment> attachments = new ArrayList<>();
		for(MultipartFile upFile : upFiles) {
			if(!upFile.isEmpty()) {
				String originalFilename = upFile.getOriginalFilename();
				String renamedFilename = HelloSpringUtils.getRenameFilename(originalFilename);
				File destFile = new File(renamedFilename);
				upFile.transferTo(destFile);

				Attachment attach = 
						Attachment.builder()
						.originalFilename(originalFilename)
						.renamedFilename(renamedFilename)
						.build();
				attachments.add(attach);

			}

		}
		log.info("member.getId() ={}",member.getId());
		ProfileDetails profile = ProfileDetails.builder()
				.memberId(member.getId())
				.state(state)
				.introduction(introduction)
				.attachments(attachments)
				.build();
		log.info("attachments = {}", attachments);
		
		result = profileRepository.updateProfile(profile);
		List<AttachmentProfileDto> attachProfile = profileRepository.findAttachmentProfileByProfileId(profileId);

		int attachmentId = attachProfile.get(0).getAttachmentId();
		log.info("attachmentId = {}", attachmentId);

		if (attachments != null && !attachments.isEmpty()) {
			for (Attachment attach : attachments) {
				attach.setId(attachmentId);
				result = profileRepository.updateAttachment(attach);

				return result;

			}
		}
		return result;
	}


	@Override
	public int defaultUpdateProfile(PrincipalDetails member, String userName) {
		List<Attachment> attachments = new ArrayList<>();
		int result = 0;
		
		Member _userName = profileRepository.findByUsername(userName);
		int profileId = _userName.getId();
		
		ProfileDetails profile = ProfileDetails.builder()
				.memberId(profileId)
				.state(State.A)
				.introduction("안녕하세요. " + _userName.getNickname() +"입니다.")
				.build();
		result = profileRepository.updateProfile(profile);
		List<AttachmentProfileDto> attachProfile = profileRepository.findAttachmentProfileByProfileId(profileId);

		int attachmentId = attachProfile.get(0).getAttachmentId();
		
		Attachment defaultAttachment = Attachment.builder()
	            .originalFilename("default.jpg")
	            .renamedFilename("default.jpg") 
	            .build();
		attachments.clear();
	    attachments.add(defaultAttachment);
		
		if (attachments != null && !attachments.isEmpty()) {
			for (Attachment attach : attachments) {
				attach.setId(attachmentId);
				result = profileRepository.updateAttachment(attach);

				return result;

			}
		}
		
		return result;
	}
	


	@Override
	public int insertProfile(State state, String introduction, PrincipalDetails member,
			List<MultipartFile> upFiles) throws IllegalStateException, IOException {
		int result = 0;
		
		List<Attachment> attachments = new ArrayList<>();
		for(MultipartFile upFile : upFiles) {
			if(!upFile.isEmpty()) {
				String originalFilename = upFile.getOriginalFilename();
				String renamedFilename = HelloSpringUtils.getRenameFilename(originalFilename);
				File destFile = new File(renamedFilename);
				upFile.transferTo(destFile);

				Attachment attach = 
						Attachment.builder()
						.originalFilename(originalFilename)
						.renamedFilename(renamedFilename)
						.build();
				attachments.add(attach);
			}
		}
		ProfileDetails profile = ProfileDetails.builder()
				.memberId(member.getId())
				.state(state)
				.introduction(introduction)
				.attachments(attachments)
				.build();
		
		
		if (attachments != null && !attachments.isEmpty()) {
			result = profileRepository.insertProfile(profile);
			for (Attachment attach : attachments) {
				attach.setId(profile.getId());
				result = profileRepository.insertAttachment(attach);
				result = profileRepository.insertAttachmentProfile();

				return result;

			}
		}
		return result;
	}

	@Override
	public int defaultCreateProfile(PrincipalDetails member, String userName) {
		int result = 0;
		List<Attachment> attachments = new ArrayList<>();
		
		Member _userName = profileRepository.findByUsername(userName);
		int profileId = _userName.getId();
		
		ProfileDetails profile = ProfileDetails.builder()
				.memberId(profileId)
				.state(State.A)
				.introduction("안녕하세요. " + _userName.getNickname() +"입니다.")
				.build();
		
		Attachment defaultAttachment = Attachment.builder()
	            .originalFilename("default.jpg")
	            .renamedFilename("default.jpg") 
	            .build();
		attachments.clear();
	    attachments.add(defaultAttachment);
		
		if (attachments != null && !attachments.isEmpty()) {
			result = profileRepository.insertProfile(profile);
			for (Attachment attach : attachments) {
				attach.setId(profile.getId());
				result = profileRepository.insertAttachment(attach);
				result = profileRepository.insertAttachmentProfile();

				return result;

			}
		}
		return result;
	}
        //        private int id;
        //        private int memberId;
        //        private State state;
        //        private String introduction;
        //        private String renamedFilename;
        //        private int follower;
        //        private int followee;


    }






















