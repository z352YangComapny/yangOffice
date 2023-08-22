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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.comments.dto.CommentCreateDto;
import com.yangworld.app.domain.comments.dto.QnaCommentCreateDto;
import com.yangworld.app.domain.comments.dto.QnaCommentDto;
import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.comments.service.CommentsService;
import com.yangworld.app.domain.comments.service.QnACommentsServiceImpl;
import com.yangworld.app.domain.photoFeed.controller.PhotoFeedController;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class commentsController {
	
	
	@Autowired
//	@Qualifier("FeedCommentsServiceImpl")
	private CommentsService commentService;
	
	@Autowired
	@Qualifier("QnACommentsServiceImpl")
	private CommentsService qnacommentService;
	
	@GetMapping("/getComments")
	public ResponseEntity<?> getComments(@RequestParam int photoFeedId) {
	    List<Comments> comments = commentService.getCommentsByPhotoFeedId(photoFeedId);
	    
	    if (comments != null && !comments.isEmpty()) {
	        return ResponseEntity.ok(comments);
	    } else {
	        return ResponseEntity.ok("no");
	    }
	}

	
	// TODO 댓글 조회
	
	// 댓글 작성
	@PostMapping("/commentCreate")
	public ResponseEntity<?> commentCreate(
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
	
	

	//----------------------------
	
	@PostMapping("/qnaCommentCreate")
    public ResponseEntity<?> qnaCommentCreate(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody @Valid QnaCommentCreateDto qnaCommentCreateDto,
            BindingResult bindingResult,
            Model model) {
		log.info("principalDetails = {}" , principalDetails);
		
		log.info("qnaComeentCreateDto = {}", qnaCommentCreateDto);
        if (bindingResult.hasErrors()) {
            // 유효성 검사 오류가 있는 경우
            return ResponseEntity.badRequest().body("Invalid data");
        }
        
        int result = qnacommentService.insertQnaComment(principalDetails, qnaCommentCreateDto);
        log.info("result = {}", result);
        if (result > 0) {
        	return ResponseEntity.ok("{\"message\": \"Comment inserted successfully.\"}");

        } else {
            return ResponseEntity.badRequest().body("Failed to insert comment.");
        }
    }
	
	@GetMapping("/getQnaComments")
	public ResponseEntity<?> getQnaComments(@RequestParam int questionId) {
	    List<Comments> qnaComments = qnacommentService.getCommentsByQuestionId(questionId);
	    
	    if (qnaComments != null && !qnaComments.isEmpty()) {
	        return ResponseEntity.ok(qnaComments);
	    } else {
	        return ResponseEntity.ok("no");
	    }
	}
}

	


	
	
	
