package com.yangworld.app.domain.comments.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.comments.dto.CommentCreateDto;
import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.comments.service.CommentsService;
import com.yangworld.app.domain.photoFeed.controller.PhotoFeedController;
import com.yangworld.app.domain.question.entity.Comment;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class commentsController {
	
	
	@Autowired
	@Qualifier("FeedCommentsServiceImpl")
	private CommentsService commentService;
	
	// 댓글 조회
	@GetMapping("/photoFeedId={photoFeedId}")
	public void getComments(@PathVariable int photoFeedId, Model model) {
	
    List<Comments> comments = commentService.getCommentsByPhotoFeedId(photoFeedId);
    
    model.addAttribute("comments", comments);
	}
	
	// 댓글 작성
	@PostMapping("/feedDetails/commentCreate")
	public String commentCreate(
	        @AuthenticationPrincipal PrincipalDetails principalDetails,
	        @RequestParam String comment,
	        @RequestParam int photoFeedId) {
	    
	    int result = commentService.insertComment(principalDetails, comment, photoFeedId);
	    
	    return "redirect:/";
	}

	
	
	// 댓글 수정
	@PostMapping("/commentUpdate")
	public ResponseEntity<?> commentUpdate(
			@AuthenticationPrincipal PrincipalDetails principalDetails,
			@RequestBody @Valid CommentCreateDto commentUpdateDto,
			BindingResult bindingResult
			){
		
		int result = commentService.updateComment(principalDetails, commentUpdateDto);
		
		
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/comentDelete")
	public ResponseEntity<?> commentDelete(
			@AuthenticationPrincipal PrincipalDetails principalDetails,
			@RequestBody @Valid CommentCreateDto commentDeleteDto,
			BindingResult bindingResult
			){
		
		int result = commentService.deleteComment(principalDetails, commentDeleteDto);
		
		
		return ResponseEntity.ok().build();
	}
	
	



	
	
	
}
