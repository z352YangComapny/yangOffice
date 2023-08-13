package com.yangworld.app.domain.photoFeed.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> peedCreate(
			@Valid PeedCreateDto _peed,
			BindingResult bindingResult,
			@AuthenticationPrincipal Member member,
			@RequestParam(value = "upFile", required = false) List<MultipartFile> upFiles) // required = false 파일을 첨부하지 않아도 요청이 성공할텐데..
					throws IllegalStateException, IOException {
		
		log.debug("_peed = {}",_peed);// 넌 왜 안 찍히냐 
		log.debug("member = {}",member); // 혼날래?
		log.debug("upFiles = {}",upFiles); // postman 요청 방식 = post : http://localhost:8080/peedCreate.do
		// Form:data
		//  Key : writerId   
		// Value : admin
		//  key : content
		// value : hello
		// why?
		
		List<Attachment> attachments = new ArrayList<>(); // 너가 문제야 너가
		for(MultipartFile upFile : upFiles){ // 너도 문제야 그냥
			if(!upFile.isEmpty()) { // 왜그러는거야 도대체 이유가 뭐야 ㅋㅋ
				String originalFilename = upFile.getOriginalFilename(); // 작성자랑 내용만 보냈는데 넌 왜 NullpointException이 나는거냐구
				String renamedFilename = HelloSpringUtils.getRenameFilename(originalFilename); // 왜 안돼 
				File destFile = new File(renamedFilename); // postman 요청방식이 틀렸나
				upFile.transferTo(destFile);
				
				// gpt형한테 물어보니 깔끔하대
				
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
		
		if (result > 0) {
	        // 성공적으로 생성되었을 경우
			return ResponseEntity.ok().build();
	    } else {
	        // 생성 중 오류가 발생한 경우
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create peed");
	    }
	}
}
