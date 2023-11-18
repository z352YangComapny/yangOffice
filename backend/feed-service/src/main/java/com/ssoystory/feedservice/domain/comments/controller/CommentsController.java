package com.ssoystory.feedservice.domain.comments.controller;

import com.ssoystory.feedservice.domain.comments.dto.CommentsDto;
import com.ssoystory.feedservice.domain.comments.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feed/comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    @PostMapping
    ResponseEntity<?> enrollComments(@RequestBody CommentsDto commentDto, @RequestHeader("x-authorization-id") long id) {
            commentsService.save(commentDto, id);
            return ResponseEntity.ok().build();
    }
}
