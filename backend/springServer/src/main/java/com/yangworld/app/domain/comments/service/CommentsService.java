package com.yangworld.app.domain.comments.service;

import javax.validation.Valid;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.comments.dto.CommentCreateDto;

@Primary
public interface CommentsService {





	int insertComment(PrincipalDetails principalDetails, @Valid CommentCreateDto commentCreateDto);
}
