package com.yangworld.app.domain.dm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.dm.dto.DmDto;
import com.yangworld.app.domain.dm.entity.Dm;
import com.yangworld.app.domain.dm.service.DmService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/dm")
public class DmController {
	
	@Autowired
	private DmService dmService;
	
	@PostMapping("/sendDm")
	public ResponseEntity<?> sendDm(@AuthenticationPrincipal PrincipalDetails principal, @RequestBody DmDto _dmDto) {
		log.info("sendDm info = {}", _dmDto);
		// senderId 가져오기
		
		 int senderId = principal.getId();
		 Dm dm = _dmDto.toDm();
		 log.info("senderId={}", senderId); 
		 dm.setSenderId(senderId);
		 
		
		// insert
		dmService.insertDm(dm);
		
		return ResponseEntity.ok().build();
	}
}
