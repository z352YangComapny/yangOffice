package com.yangworld.app.domain.comments.controller;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.comments.service.CommentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class commentsController {

    @Autowired
    @Qualifier("FeedCommentsServiceImpl")
    private CommentsService commentsService;


    @Autowired
//    @Qualifier("QnACommentsServiceImpl")
    private CommentsService qnaCommentService;

    @PatchMapping("/commentsUpdate")
    public ResponseEntity<?> commentsUpdate(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestParam int commentId,
            @RequestParam String content
            ){

        int result = commentsService.commentsUpdate(principalDetails, commentId, content);

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/commentsDelete")
    public ResponseEntity<?> commentsDelete(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestParam int commentId
    ){

        int result = commentsService.commentsDelete(principalDetails, commentId);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/commentCreate")
    public ResponseEntity<?> commentCreate(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestParam String content,
            @RequestParam int feedId
    ){

        int result = commentsService.commentCreate(principalDetails, content, feedId);

        return ResponseEntity.ok().body(result);
    }



}
