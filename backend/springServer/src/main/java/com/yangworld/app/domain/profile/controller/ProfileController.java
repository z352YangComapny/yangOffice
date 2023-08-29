package com.yangworld.app.domain.profile.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import com.yangworld.app.domain.member.dto.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.yangworld.app.commons.FileUploadUtils;
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
/*@RequestMapping("/profile")*/
//@RequestMapping("/member/userPage/{id}/profile")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;

	@Autowired
	private ServletContext application;
	
	/*@GetMapping("/create.do")
	public String showCreateProfileForm(Model model) {
	    ProfileDto profile = new ProfileDto();
	    // 필요한 필드들을 설정
	    profile.getState();
	    profile.getIntroduction();

	    model.addAttribute("profile", profile);
	    return "/profile/profileCreate";
	}*/
	@GetMapping("/profile/profileCreate")
	public String  profileCreate(@ModelAttribute("member") SignUpDto signUpDto) {
		return "redirect:/profile/profileCreate";
	}

	@GetMapping("/member/userPage/{id}/profile/update.do")
	public String showUpdateProfileForm(Model model, @AuthenticationPrincipal PrincipalDetails principal) {
	    int memberId = principal.getId();

//	    log.info("upPrincipalId = {} ", principal.getId());
	    // 프로필 정보 가져오기
	    ProfileDetails profile = profileService.getProfileByMemberId(memberId);
//	    log.info("profile = {}", profile);
	    // 프로필 사진 가져오기
	    List<Attachment> profileAttachments = profileService.getAttachmentsByProfileId(profile.getId());

	    model.addAttribute("profile", profile);
	    model.addAttribute("profileAttachments", profileAttachments);
	    model.addAttribute("principalBday", principal.getBirthday());
	    
	    model.addAttribute("principalName", principal.getName());


	    return "profile/profileUpdate";

	}
	@GetMapping("/member/userPage/{id}/profile/profileMain")
	@PreAuthorize("isAuthenticated()")
	public String profileMain(@PathVariable("id") int id, Model model, @AuthenticationPrincipal PrincipalDetails principal) {
		
		
		//int memberId = principal.getId();
//		log.info("principal = {} ", principal.getId());
		ProfileDetails profile = profileService.getProfileByMemberId(id);
	    
	    List<Attachment> profileAttachments = profileService.getAttachmentsByProfileId(profile.getId());
	   
	    model.addAttribute("profile", profile);
	    log.info("profile@main={}", profile);
	    model.addAttribute("profileAttachments", profileAttachments);
	    model.addAttribute("principalBday", principal.getBirthday());
	    model.addAttribute("principalName", principal.getName());
	    model.addAttribute("principalGender", principal.getGender());
	    model.addAttribute("principalId", principal.getId());
	    model.addAttribute("profileId", profile.getId());
	    model.addAttribute("profileMemberId",profile.getMemberId());
	    log.info("profile = {}", profile);
	    log.info("principalId ={}", principal.getId());
//	    log.info("profileAttachment = {}",profileAttachments);
	    
	    
	    return "member/userPage";

	}


	@PostMapping("/profile/profileCreate")
	public String profileCreate(
			@Valid ProfileDto _profile,
			BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails principal,
			@RequestParam(value = "upFile", required = false) List<MultipartFile> upFiles) 
					throws IllegalStateException, IOException {
		log.info("_ProfileDto ={}", _profile);
//		log.info("_profile = {}", _profile);
//		log.info("principal = {}",principal); 
//		log.info("upFiles = {}", upFiles); 
//		log.info("principal = {}", principal.getId());

		// 이미지 상대경로 지정
		String saveDirectory = application.getRealPath("/resources/upload/attachment");
		List<Attachment> attachments = new ArrayList<>(); 
		for(MultipartFile upFile : upFiles){
			if(!upFile.isEmpty()) { 
				String originalFilename = upFile.getOriginalFilename(); 
				String renamedFilename = FileUploadUtils.getRenameFilename(originalFilename);  
				File destFile = new File(saveDirectory, renamedFilename);
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
				.memberId(_profile.getMemberId())
				.state(_profile.getState())
				.introduction(_profile.getIntroduction())
				.attachments(attachments)
				.build();
		
		int result = profileService.insertProfile(profile);

		log.info("result@profileCreate={}", result);

		if (result > 0) {
	        // 성공적으로 생성되었을 경우
			return "redirect:/member/memberLogin.do";
	    } else {
	        // 생성 중 오류가 발생한 경우
	        return "redirect:/profile/profileCreate";
	    }
	}
	
	@PostMapping("/member/userPage/{id}/profile/update.do")
	public String update(
	    @Valid ProfileDto _profile,
	    BindingResult bindingResult,
	    @AuthenticationPrincipal PrincipalDetails principal,
	    @RequestPart(value = "upFile", required = false) List<MultipartFile> upFiles, Model model
	) throws IllegalStateException, IOException {
	    int memberId = principal.getId();
	    
	    ProfileDetails originalProfile = profileService.getProfileByMemberId(memberId);
	    log.info("profile = {}", _profile);
	    log.info("upfiles = {}", upFiles);

		// 이미지 상대경로 지정
		String saveDirectory = application.getRealPath("/resources/upload/attachment");
	    List<Attachment> attachments = new ArrayList<>();
	    
	        for (MultipartFile upFile : upFiles) {
	            if (!upFile.isEmpty()) {
	                String originalFilename = upFile.getOriginalFilename();
	                String renamedFilename = FileUploadUtils.getRenameFilename(originalFilename);
	                File destFile = new File(saveDirectory, renamedFilename);
	                upFile.transferTo(destFile);
	                
	                Attachment attach =  
	                    Attachment.builder()
	                    .originalFilename(originalFilename)
	                    .renamedFilename(renamedFilename)
	                    .build();
	                attachments.add(attach);
	            }
	        }
	    
	    ProfileDetails updatedProfile = ProfileDetails.builder()
	        .id(originalProfile.getId())
	        .memberId(principal.getId())
	        .state(_profile.getState())
	        .introduction(_profile.getIntroduction())
	        .attachments(attachments)
	        .build();
	    
	    log.info("updatedProfile = {}", updatedProfile);
	    log.info("principalId = {}", principal.getId()); 
	    
	    int result = profileService.updateProfile(updatedProfile);
	    log.debug("updatedProfile = {}", updatedProfile);
	    log.debug("result = {}", result);
	    
	    model.addAttribute("profile", updatedProfile);
	    
	    return "redirect:/member/userPage/" + principal.getId();
	}

	
	
	
	
	
	@PostMapping("/member/userPage/{id}/profile/defaultcreate.do")
	public ResponseEntity<?> createDefaultProfile(@AuthenticationPrincipal PrincipalDetails principal) {
	    int memberId = principal.getId();
	    ProfileDetails profile = ProfileDetails.builder()
	            .memberId(memberId)
	            .state(State.A)
	            .introduction("안녕하세요, " + principal.getUsername() + "입니다.")
	            .build();

	    // 첨부 파일 생성 및 추가
	    Attachment defaultAttachment = Attachment.builder()
	            .originalFilename("default.jpg")
	            .renamedFilename("default.jpg") // 실제 파일명으로 수정
	            .build();
	    List<Attachment> attachments = new ArrayList<>();
	    attachments.add(defaultAttachment);
	    profile.setAttachments(attachments);

	    int result = profileService.insertProfile(profile);

	    if (result > 0) {
	        return ResponseEntity.ok().build();
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to insert profile");
	    }
	}



	@PostMapping("/member/userPage/{id}/profile/defaultupdate")
    public String defaultUpdateProfile(
    		@AuthenticationPrincipal PrincipalDetails principal,
    		Model model) {
		
		int memberId = principal.getId();
		ProfileDetails _profile = profileService.getProfileByMemberId(memberId);
	    ProfileDetails profile = ProfileDetails.builder()
	    		.id(_profile.getId())
	            .memberId(memberId)
	            .state(State.A)
	            .introduction("안녕하세요, " + principal.getUsername() + "입니다.")
	            .build();
	    
	    Attachment defaultAttachment = Attachment.builder()
	            .originalFilename("default.jpg")
	            .renamedFilename("default.jpg") // 실제 파일명으로 수정
	            .build();
	    List<Attachment> attachments = new ArrayList<>();
	    attachments.clear();
	    attachments.add(defaultAttachment);
	    profile.setAttachments(attachments);
	    log.info("defaultprofile = {}", profile);

	    int result = profileService.updateProfile(profile);

        // 수정 페이지로 리다이렉트
        return "profile/profileUpdate";
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	@PostMapping("/reset")
	public ResponseEntity<?> reset(@AuthenticationPrincipal PrincipalDetails principal, ProfileDto profileDto){
		int memberId = principal.getId();
		
		profileDto.setMemberId(memberId);
		profileService.resetProfile(memberId);
		
		return ResponseEntity.ok().build();
	}
}

