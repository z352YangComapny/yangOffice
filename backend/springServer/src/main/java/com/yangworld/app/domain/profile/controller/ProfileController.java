package com.yangworld.app.domain.profile.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.yangworld.app.commons.HelloSpringUtils;
import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.member.dto.UpdateDto;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.profile.dto.ProfileDto;
import com.yangworld.app.domain.profile.entity.Profile;
import com.yangworld.app.domain.profile.entity.ProfileDetails;
import com.yangworld.app.domain.profile.entity.State;
import com.yangworld.app.domain.profile.service.ProfileService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	@GetMapping("/create.do")
	public String showCreateProfileForm(Model model) {
	    ProfileDto profile = new ProfileDto();
	    // 필요한 필드들을 설정
	    profile.getState();
	    profile.getIntroduction();

	    model.addAttribute("profile", profile);
	    return "/profile/profileCreate";
	}
	
	
	
	
//	@PostMapping("/create")
//	public ResponseEntity<?> create(@AuthenticationPrincipal PrincipalDetails principal, @RequestBody ProfileDto profileDto){
//		
//		int memberId = principal.getId();
//		log.debug("memberId info = {} ", memberId);
//		
//		
//		profileDto.setMemberId(memberId);
//		profileService.insertProfile(profileDto);
//		
//		return ResponseEntity.ok().build();
//	}
	
//	@PostMapping("/update")
//	public ResponseEntity<?> update(@AuthenticationPrincipal PrincipalDetails principal, @RequestBody ProfileDto profileDto){
//		
//		int memberId = principal.getId();
//		profileDto.setMemberId(memberId);
//		profileService.updateProfile(profileDto);
//		
//		return ResponseEntity.ok().build();
//	}
	
	

	@PostMapping("/create.do")
	public ResponseEntity<?> create(
			@Valid ProfileDto _profile,
			BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails principal,
			@RequestParam(value = "upFile", required = false) List<MultipartFile> upFiles) 
					throws IllegalStateException, IOException {
		
		log.info("_profile = {}", _profile);
		log.info("principal = {}",principal); 
		log.info("upFiles = {}", upFiles); 
		log.info("principal = {}", principal.getId());
		
		List<Attachment> attachments = new ArrayList<>(); 
		for(MultipartFile upFile : upFiles){
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
				.memberId(principal.getId())
				.state(_profile.getState())
				.introduction(_profile.getIntroduction())
				.attachments(attachments)
				.build();
		
		
		int result = profileService.insertProfile(profile);
		
		if (result > 0) {
	        // 성공적으로 생성되었을 경우
			return ResponseEntity.ok().build();
	    } else {
	        // 생성 중 오류가 발생한 경우
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert profile");
	    }
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(
			@RequestPart @Valid ProfileDto _profile,
			BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails principal,
			@RequestPart(value = "upFile", required = false) List<MultipartFile> upFiles) 
					throws IllegalStateException, IOException {
		
		List<Attachment> attachments = new ArrayList<>(); 
		for(MultipartFile upFile : upFiles){
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
				.id(_profile.getId())
				.memberId(principal.getId())
				.state(_profile.getState())
				.introduction(_profile.getIntroduction())
				.attachments(attachments)
				.build();
		
		log.info("_profile = {}", _profile);
		log.info("principal = {}",principal); 
		log.info("upFiles = {}", upFiles); 
		
		int result = profileService.updateProfile(profile);
		log.debug("profile ={}", profile);
		log.debug("result ={}", result);
		
		if (result > 0) {
			
			return ResponseEntity.ok().build();
	    } else {
	    	
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update profile");
	    }
	}
	
	@PostMapping("/defaultcreate.do")
	public ResponseEntity<?> createDefaultProfile(
	        @AuthenticationPrincipal PrincipalDetails principal) {

	    int memberId = principal.getId();

	    ProfileDetails profile = ProfileDetails.builder()
	            .memberId(memberId)
	            .state(State.A)
	            .introduction("안녕하세요, " + principal.getUsername() + "입니다.")
	            .build();

	    // 기본 사진의 파일 경로
	    String defaultImagePath = "/Users/hongseung-young/Documents/GitHub/yangOffice/backend/springServer/src/main/webapp/resources/upload/profile/default.jpg"; // 실제 경로로 수정

	    Attachment defaultAttachment = Attachment.builder()
	            .originalFilename("default.jpg")
	            .renamedFilename("default.jpg") // 실제 파일명으로 수정
	            .build();

	    profile.getAttachments().add(defaultAttachment);

	    int result = profileService.insertProfile(profile);

	    if (result > 0) {
	        return ResponseEntity.ok().build();
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert profile");
	    }
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	@PostMapping("/reset")
	public ResponseEntity<?> reset(@AuthenticationPrincipal PrincipalDetails principal, ProfileDto profileDto){
		int memberId = principal.getId();
		
		profileDto.setMemberId(memberId);
		profileService.resetProfile(memberId);
		
		return ResponseEntity.ok().build();
	}
}

