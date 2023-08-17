package com.yangworld.app.domain.comments.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.comments.dto.CommentCreateDto;
import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.comments.service.CommentsService;
import com.yangworld.app.domain.photoFeed.controller.PhotoFeedController;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class commentsController {
	
	
	@Autowired
	@Qualifier("FeedCommentsServiceImpl")
	private CommentsService commentService;
	
	// TODO 댓글 조회, 댓글 수정, 댓글 삭제
	
	
	@PostMapping("/feedDetails/commentCreate")
	public ResponseEntity<String> commentCreate(
	        @AuthenticationPrincipal PrincipalDetails principalDetails,
	        @RequestBody @Valid CommentCreateDto commentCreateDto,
	        BindingResult bindingResult) {
		
		log.info("principalDetails = {}", principalDetails.getId());
		
		
	    int result = commentService.insertComment(principalDetails, commentCreateDto);

	    if (result > 0) {
	        return ResponseEntity.ok("Comment inserted successfully.");
	    } else {
	        return ResponseEntity.badRequest().body("Failed to insert comment.");
	    }
	}
	
	



	
	
	
}
