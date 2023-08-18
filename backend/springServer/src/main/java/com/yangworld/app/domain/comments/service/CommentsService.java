package com.yangworld.app.domain.comments.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.comments.dto.CommentCreateDto;
import com.yangworld.app.domain.comments.entity.Comments;

@Primary
public interface CommentsService {





	int insertComment(PrincipalDetails principalDetails,  CommentCreateDto commentCreateDto);

	int updateComment(PrincipalDetails principalDetails,  CommentCreateDto commentUpdateDto);

	int deleteComment(PrincipalDetails principalDetails,  CommentCreateDto commentDeleteDto);

	List<Comments> getCommentsByPhotoFeedId(int photoFeedId);
}
