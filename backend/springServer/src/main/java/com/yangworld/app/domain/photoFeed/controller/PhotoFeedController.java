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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.yangworld.app.commons.HelloSpringUtils;
import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.comments.service.CommentsService;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.service.MemberService;
import com.yangworld.app.domain.photoFeed.dto.FeedCreateDto;
import com.yangworld.app.domain.photoFeed.dto.FeedDeleteDto;
import com.yangworld.app.domain.photoFeed.dto.PhotoAttachmentFeedDto;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import com.yangworld.app.domain.photoFeed.entity.Like;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;
import com.yangworld.app.domain.photoFeed.service.PhotoFeedService;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@Slf4j
//@RequestMapping("/feed")
public class PhotoFeedController {
	
	@Autowired
	private PhotoFeedService photoFeedService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CommentsService commentsService;
	
	@GetMapping("/somePage")
	public String somePage(Model model) {
	    // 실제 값을 설정하여 모델에 추가
	    String writer = "some_writer_value";
	    int photoFeedId = 123; // 실제 값으로 변경

	    model.addAttribute("writer", writer);
	    model.addAttribute("photoFeedId", photoFeedId);

	    return "your_html_page"; // 실제 페이지의 이름으로 변경
	}

	
	/**
	 * 회원 조회
	 */
	@GetMapping("/feed/list")
	public ResponseEntity<?> selectFeed(
			@RequestParam int writerId,
			Model model
			) {
		
		// GET = http://localhost:8080/JS
		
		List<PhotoAttachmentFeedDto> photoList = photoFeedService.selectFeed(writerId); 
		
		return ResponseEntity.ok(photoList);
	}
	
	
	@GetMapping("/feedDetails/{writer}/{photoFeedId}")
	public ResponseEntity<?> findById(@PathVariable int writerId, @PathVariable int photoFeedId) {
	    try {
	        // 단계 2: 데이터베이스에서 필요한 정보 조회
	        Member member = memberService.findByUsername(writerId);
	        PhotoFeed photoFeed = photoFeedService.findById(photoFeedId);
	        List<Comments> comments = commentsService.getCommentsByPhotoFeedId(photoFeedId);
	        List<Like> likesCount = photoFeedService.getLikesCountByPhotoFeedId(photoFeedId);

	        log.info("member ={}", member);
	        log.info("photoFeed = {}", photoFeed);
	        log.info("comments = {}", comments);
	        log.info("likesCount = {}", likesCount);

	        // 단계 4: 필요한 정보 가공하여 반환
	        FeedDetails response = FeedDetails.builder()
	                .id(photoFeedId)
	                .member(member)
	                .like(likesCount)
	                .comments(comments)
	                .build();

	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        // 예외 처리
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
	    }
	}


	
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
	
	
	
	
	@PostMapping("/feedDelete")
	public ResponseEntity<?> deleteFeed(@RequestParam int feedId){
//		DELETE FROM attachment_photo_feed WHERE photo_feed_id = [피드의 ID];
//		DELETE FROM photo_feed WHERE id = [피드의 ID];
//		DELETE FROM attachment
//		WHERE id NOT IN (SELECT attachment_id FROM attachment_photo_feed);
		
		 int result = photoFeedService.deleteFeed(feedId);
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/feedUpdate")
	public ResponseEntity<?> updateFeed(
			@RequestParam int feedId,
			@RequestParam String content
			){
		
		int result = photoFeedService.updateFeed(feedId, content);
		
		return null;
	}
	
	// 좋아요 넣기 
	@PostMapping("/feedLikeUpdate")
	public ResponseEntity<?> insertLike(
			@RequestParam int photoFeedId,
			@RequestParam int memberId
			){
		
		int result = photoFeedService.insertLike(photoFeedId, memberId);
			
		return null;
	}
	
	@PostMapping("/feedLikeDelete")
	public ResponseEntity<?> deleteLike(
			@RequestParam int photoFeedId,
			@RequestParam int memberId
			){
		
		int result = photoFeedService.deleteLike(photoFeedId, memberId);
		
		return null;
	}
	
	
	
	
	

	

	
	
	
	
	
	
	
	
	
	
}
