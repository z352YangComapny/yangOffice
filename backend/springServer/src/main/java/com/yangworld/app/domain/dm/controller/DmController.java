package com.yangworld.app.domain.dm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yangworld.app.domain.dm.dto.DmDto;
import com.yangworld.app.domain.dm.service.DmService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/dm")
public class DmController {
	
	@Autowired
	private DmService dmService;
	
	@PostMapping("/sendDm")
	public ResponseEntity<?> sendDm(@RequestBody DmDto dmDto) {
		log.info("sendDm info = {}", dmDto);
		dmService.insertDm(dmDto);
		
		return ResponseEntity.ok().build();
	}
}
