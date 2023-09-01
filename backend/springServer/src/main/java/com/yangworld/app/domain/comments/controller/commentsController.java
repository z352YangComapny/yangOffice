package com.yangworld.app.domain.comments.controller;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.comments.service.CommentsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
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
            @RequestParam int id
            ){
        // content, id
        // update comments set content = #{content} where id = #{id}
        int result = commentsService.commentsUpdate(principalDetails, id);

        return null;
    }



}
