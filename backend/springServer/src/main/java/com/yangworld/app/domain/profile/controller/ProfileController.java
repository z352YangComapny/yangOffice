package com.yangworld.app.domain.profile.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.profile.dto.ProfileDto;
import com.yangworld.app.domain.profile.entity.Profile;
import com.yangworld.app.domain.profile.service.ProfileService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@AuthenticationPrincipal PrincipalDetails principal, @RequestBody ProfileDto profileDto){
		
		int memberId = principal.getId();
		log.info("memberId info = {} ", memberId);
		
		
		profileDto.setMemberId(memberId);
		profileService.insertProfile(profileDto);
		
		return ResponseEntity.ok().build();
	}
	
}

