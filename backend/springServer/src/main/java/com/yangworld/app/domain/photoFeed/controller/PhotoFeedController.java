package com.yangworld.app.domain.photoFeed.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yangworld.app.commons.HelloSpringUtils;
import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.photoFeed.dto.PeedCreateDto;
import com.yangworld.app.domain.photoFeed.entity.PeedDetails;
import com.yangworld.app.domain.photoFeed.service.PhotoPeedService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PhotoFeedController {
	
	@Autowired
	private PhotoPeedService photoPeedService;
	
	
	@PostMapping("peedCreate.do")
	public String peedCreate(
			@Valid PeedCreateDto _peed,
			BindingResult bindingResult,
			@AuthenticationPrincipal Member member,
			@RequestParam(value = "upFile", required = false) List<MultipartFile> upFiles)
					throws IllegalStateException, IOException {
		
		
		List<Attachment> attachments = new ArrayList<>();
		for(MultipartFile upFile : upFiles){
			if(!upFile.isEmpty()) {
				String originalFilename = upFile.getOriginalFilename();
				String renamedFilename = HelloSpringUtils.getRenameFilename(originalFilename); // 20230807_142828888_123.jpg
				File destFile = new File(renamedFilename); // 부모디렉토리 생략가능. spring.servlet.multipart.location 값을 사용
				upFile.transferTo(destFile);
				
				Attachment attach = 
						Attachment.builder()
						.originalFilename(originalFilename)
						.renamedFilename(renamedFilename)
						.build();
				attachments.add(attach);
			}
		}
		
		PeedDetails peed = PeedDetails.builder()
				.writerId(_peed.getWriterId())
				.content(_peed.getContent())
				.attachments(attachments)
				.build();
		
		
		int result = photoPeedService.insertPeed(peed);
		
		return null;
	}
}
