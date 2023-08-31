package com.yangworld.app.domain.profile.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yangworld.app.commons.HelloSpringUtils;
import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.profile.dto.ProfileDto;
import com.yangworld.app.domain.profile.entity.ProfileDetails;
import com.yangworld.app.domain.profile.service.ProfileService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/profile/{id}")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;

	@Autowired
	private ServletContext application;
	
//	@PutMapping("")
//	public ResponseEntity<?> profileCreate(
//			@Valid ProfileDto _profile,
//			BindingResult bindingResult,
//			@AuthenticationPrincipal PrincipalDetails principal,
//			@RequestParam(value = "upFile", required = false) List<MultipartFile> upFiles) 
//					throws IllegalStateException, IOException {
//
//		String saveDirectory = application.getRealPath("/resources/upload/attachment");
//		List<Attachment> attachments = new ArrayList<>(); 
//		for(MultipartFile upFile : upFiles){
//			if(!upFile.isEmpty()) { 
//				String originalFilename = upFile.getOriginalFilename(); 
//				String renamedFilename = HelloSpringUtils.getRenameFilename(originalFilename);  
//				File destFile = new File(saveDirectory, renamedFilename);
//				upFile.transferTo(destFile);
//				
//				Attachment attach =  
//						Attachment.builder()
//						.originalFilename(originalFilename)
//						.renamedFilename(renamedFilename)
//						.build();
//				attachments.add(attach);
//			}
//		}
//		
//		ProfileDetails profile = ProfileDetails.builder()
//				.memberId(_profile.getMemberId())
//				.state(_profile.getState())
//				.introduction(_profile.getIntroduction())
//				.attachments(attachments)
//				.build();
//		
//		int result = profileService.insertProfile(profile);
//
//
//		if (result > 0) {
//	        // 성공적으로 생성되었을 경우
//			return ResponseEntity.ok().build();
//	    } else {
//	        // 생성 중 오류가 발생한 경우
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create profile");
//	    }
//	}
	
	
	
}
