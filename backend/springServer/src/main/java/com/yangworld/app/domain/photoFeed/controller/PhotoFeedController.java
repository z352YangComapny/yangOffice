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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.yangworld.app.commons.HelloSpringUtils;
import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.photoFeed.dto.FeedCreateDto;
import com.yangworld.app.domain.photoFeed.dto.FeedDeleteDto;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;
import com.yangworld.app.domain.photoFeed.service.PhotoFeedService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PhotoFeedController {
	
	@Autowired
	private PhotoFeedService photoFeedService;
	
	
	@PostMapping("/feedCreate")
	public ResponseEntity<?> peedCreate(
			@RequestPart @Valid FeedCreateDto _feed,
			BindingResult bindingResult,
			@AuthenticationPrincipal Member member,
			@RequestPart(value = "upFile", required = false) List<MultipartFile> upFiles) // required = false 파일을 첨부하지 않아도 요청이 성공
					throws IllegalStateException, IOException {
		
		
		log.debug("_feed = {}",_feed);
		log.debug("member = {}",member); 
		log.debug("upFiles = {}",upFiles); // postman 요청 방식 = post : http://localhost:8080/peedCreate.do
		
		
		
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
		
		FeedDetails feed = FeedDetails.builder()
				.writerId(member.getId())
				.content(_feed.getContent())
				.attachments(attachments)
				.build();
		
		 
		int result = photoFeedService.insertFeed(feed);
		
		if (result > 0) {
	        // 성공적으로 생성되었을 경우
			return ResponseEntity.ok(feed);
	    } else {
	        // 생성 중 오류가 발생한 경우
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create Feed");
	    }
	}
	
	/**
	 * 회원 조회
	 */
	@GetMapping("/{nickName}")
	public ResponseEntity<?> selectFeed(
			@PathVariable String nickName, // 바꾸기
			Model model
			) {
		
		// GET = http://localhost:8080/JS
		
		log.debug(nickName);
		log.debug("nickName = {}",nickName);
		
		PhotoFeed photoFeed = photoFeedService.selectFeed(nickName); 
		
		return ResponseEntity.ok(photoFeed);
	}
	
	
	// 피드안 댓글 + 사진 + 글내용 + 제목 + 작성자정보;
	@GetMapping("/feedDetail/{nickName}")
	public ResponseEntity<?> detailFeed(
			@RequestPart @Valid Member member,
			@PathVariable String nickName,
			BindingResult bindingResult
			){
		
		return null;
	}
	
	
	
	@PostMapping("/feedDelete")
	public ResponseEntity<?> deleteFeed(
			@AuthenticationPrincipal FeedDeleteDto feed,
			Model model
			){
		
		 int result = photoFeedService.deleteFeed(feed);
		
		return ResponseEntity.ok().build();
	}

	
	@PostMapping("/feedUpdate")
	public ResponseEntity<?> updateFeed(
			@RequestPart @Valid FeedCreateDto _feed,
			BindingResult bindingResult,
			@AuthenticationPrincipal Member member,
			@RequestPart(value = "upFile", required = false) List<MultipartFile> upFiles
			){
		return null;
	}
	
	
	
	
	
	
	
	
	
	
}
