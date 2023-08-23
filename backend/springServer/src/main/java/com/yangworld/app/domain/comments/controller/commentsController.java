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
import com.yangworld.app.domain.comments.dto.CommentAllDto;
import com.yangworld.app.domain.comments.dto.CommentCreateDto;
import com.yangworld.app.domain.comments.dto.QnaCommentAllDto;
import com.yangworld.app.domain.comments.dto.QnaCommentDto;
import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.comments.service.CommentsService;
import com.yangworld.app.domain.comments.service.QnACommentsServiceImpl;
import com.yangworld.app.domain.photoFeed.controller.PhotoFeedController;
import com.yangworld.app.domain.question.entity.Comment;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class commentsController {
	
	
	@Autowired
//	@Qualifier("FeedCommentsServiceImpl")
	private CommentsService commentService;
	

	@Autowired
	@Qualifier("QnACommentsServiceImpl")
	private CommentsService qnaCommentService;
	
	@GetMapping("/getComments")
	public ResponseEntity<?> getComments(@RequestParam int photoFeedId) {
	    List<CommentAllDto> comments = commentService.getCommentsByPhotoFeedId(photoFeedId);
	    
	    if (comments != null && !comments.isEmpty()) {
	        return ResponseEntity.ok(comments);
	    } else {
	        return ResponseEntity.ok("no");
	    }
	}

	
	// TODO 댓글 조회

//	// 댓글 조회
//	@GetMapping("/feed/feedDetail")
//	public void getComments(
//			@AuthenticationPrincipal PrincipalDetails principalDetails,
//			@RequestParam int photoFeedId,
//			Model model) {
//	
//		List<Comments> comments = commentService.getCommentsByPhotoFeedId(photoFeedId);
//    
//		model.addAttribute("comments", comments);
//	}

	
	// 댓글 작성
	@PostMapping("/feedDetails/commentCreate")
	public String commentCreate(
	        @AuthenticationPrincipal PrincipalDetails principalDetails,
	        @RequestParam String comment,
	        @RequestParam int photoFeedId) {
	    
	    int result = commentService.insertComment(principalDetails, comment, photoFeedId);
	    
	    return "redirect:/feed/feedDetail?photoFeedId=" + photoFeedId;
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
	
	@PostMapping("/commentDelete")
	public String commentDelete(
			@AuthenticationPrincipal PrincipalDetails principalDetails,
			@RequestParam String comment,
	        @RequestParam int photoFeedId
			){
		log.info("comment ={}", comment);
		int result = commentService.deleteComment(principalDetails, photoFeedId, comment);
		
		return "redirect:/feed/feedDetail?photoFeedId=" + photoFeedId;
	}
	
	

	//----------------------------
	
	@PostMapping("/qnaCommentCreate")
    public ResponseEntity<?> qnaCommentCreate(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody @Valid QnaCommentAllDto qnaCommentAllDto,
            BindingResult bindingResult,
            Model model) {
		log.info("principalDetails = {}" , principalDetails);
		
		log.info("qnaComeentCreateDto = {}", qnaCommentAllDto);
        if (bindingResult.hasErrors()) {
            // 유효성 검사 오류가 있는 경우
            return ResponseEntity.badRequest().body("Invalid data");
        }
        
        int result = qnaCommentService.insertQnaComment(principalDetails, qnaCommentAllDto);
        log.info("result = {}", result);
        if (result > 0) {
        	return ResponseEntity.ok("{\"message\": \"Comment inserted successfully.\"}");

        } else {
            return ResponseEntity.badRequest().body("Failed to insert comment.");
        }
    }
	
	@GetMapping("/getQnaComments")
	public ResponseEntity<?> getQnaComments(@RequestParam int questionId) {
	    List<Comments> qnaComments = qnaCommentService.getCommentsByQuestionId(questionId);
	    
	    if (qnaComments != null && !qnaComments.isEmpty()) {
	        return ResponseEntity.ok(qnaComments);
	    } else {
	        return ResponseEntity.ok("no");
	    }
	}
	@PostMapping("/qnaCommentUpdate")
	public ResponseEntity<?> qnaCommentUpdate(
	        @AuthenticationPrincipal PrincipalDetails principalDetails,
	        @RequestBody @Valid QnaCommentAllDto qnaCommentAllDto,
	        BindingResult bindingResult,
	        Model model) {
	    if (bindingResult.hasErrors()) {
	        // 유효성 검사 오류가 있는 경우
	        return ResponseEntity.badRequest().body("Invalid data");
	    }
	    
	    log.info("principalDetails = {}", principalDetails);
	    log.info("qnaCommentUpdateDto = {}", qnaCommentAllDto);



	    int result = qnaCommentService.updateQnaComment(principalDetails, qnaCommentAllDto);
	    log.info("result = {}", result);

	    if (result > 0) {
	        return ResponseEntity.ok("{\"message\": \"Comment updated successfully.\"}");
	    } else {
	        return ResponseEntity.badRequest().body("Failed to update comment.");
	    }
	}
}

	


	
	
	
