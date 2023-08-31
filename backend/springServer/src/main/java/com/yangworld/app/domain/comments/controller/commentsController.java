package com.yangworld.app.domain.comments.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.comments.dto.CommentAllDto;
import com.yangworld.app.domain.comments.dto.CommentCreateDto;
import com.yangworld.app.domain.comments.dto.QnaCommentAllDto;
import com.yangworld.app.domain.comments.dto.QnaCommentDto;
import com.yangworld.app.domain.comments.dto.CommentUpdateDto;
import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.comments.service.CommentsService;
import com.yangworld.app.domain.comments.service.QnACommentsServiceImpl;
import com.yangworld.app.domain.photoFeed.controller.PhotoFeedController;
import com.yangworld.app.domain.question.entity.Comment;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
//@RequestMapping("/member/userPage/{id}")
public class commentsController {


    @Autowired
    @Qualifier("FeedCommentsServiceImpl")
    private CommentsService commentService;


    @Autowired
	@Qualifier("QnACommentsServiceImpl")
    private CommentsService qnaCommentService;

    @GetMapping("/member/userPage/{id}/getComments")
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
    @PostMapping("/member/userPage/{id}/feedDetails/commentCreate")
    public String commentCreate(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestParam String comment,
            @RequestParam int photoFeedId,
            @PathVariable("id") int id
    ) {

        int result = commentService.insertComment(principalDetails, comment, photoFeedId);

        return "redirect:/member/userPage/" + id + "/feed/feedDetail?photoFeedId=" + photoFeedId;
    }

    // 	댓글 수정
    @PostMapping("/member/userPage/{id}/feedDetails/commentUpdate")
    public ResponseEntity<String> commentUpdate(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestParam int commentId,
            @RequestParam String content,
            @PathVariable("id") int id
    ) {

        log.info("commentId = {}", commentId);
        log.info("newContent = {}", content);

        int result = commentService.updateComment(principalDetails, content, commentId);

        if (result > 0) {
            return ResponseEntity.ok("댓글이 수정되었습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 수정에 실패하였습니다.");
        }
    }

    @PostMapping("/member/userPage/{id}/feedDetails/commentDelete")
    public String commentDelete(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestParam int commentId,
            @RequestParam int photoFeedId,
            @PathVariable("id") int id
    ) {
        int result = commentService.deleteComment(commentId);

        return "redirect:/member/userPage/" + id + "/feed/feedDetail?photoFeedId=" + photoFeedId;
    }


    //----------------------------@@@@@@@@@@@@@@@@@@----------------------------@@@@@@@@@@@@@@@@@@

    @PostMapping("/qnaCommentCreate")
    public ResponseEntity<?> qnaCommentCreate(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody @Valid QnaCommentAllDto qnaCommentAllDto,
            BindingResult bindingResult,
            Model model) {
        log.info("principalDetails = {}", principalDetails);

        log.info("qnaComeentCreateDto = {}", qnaCommentAllDto);
        if (bindingResult.hasErrors()) {
            // 유효성 검사 오류가 있는 경우
            return ResponseEntity.badRequest().body("Invalid data");
        }

        int result = qnaCommentService.insertQnaComment(principalDetails, qnaCommentAllDto);
        log.info("result = {}", result);
        if (result > 0) {
           // return ResponseEntity.ok("{\"message\": \"Comment inserted successfully.\"}");
            return ResponseEntity.ok().body(Map.of("qnaComments",  qnaCommentAllDto.getContent()));

        } else {
            return ResponseEntity.badRequest().body("Failed to insert comment.");
        }
    }

    @GetMapping("/getQnaComments")
    public ResponseEntity<?> getQnaComments(@RequestParam int questionId) {
        List<Comments> qnaComments = qnaCommentService.getCommentsByQuestionId(questionId);
        log.info("coomentsList={}", qnaComments);

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

    @PostMapping("/qnaCommentDelete")
    public ResponseEntity<?> qnaCommentDelete(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody @Valid QnaCommentAllDto qnaCommentAllDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid data");
        }

        int questionId = qnaCommentAllDto.getCommentQna().getQuestionId(); // questionId를 가져옵니다.
        log.info("qnaCommentDeleteDto = {}", qnaCommentAllDto);
        log.info("questionId = {}", questionId);
        List<Comments> qnaComments = qnaCommentService.getCommentsByQuestionId(questionId);
        int commentId = qnaComments.get(0).getId();
        log.info("commentId = {} ", commentId);
        // 댓글 삭제 로직
        int result = qnaCommentService.deleteQnaComment(commentId);

        if (result > 0) {
            return ResponseEntity.ok("{\"message\": \"Comment delete successfully.\"}");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete comment.");
        }
    }


}







