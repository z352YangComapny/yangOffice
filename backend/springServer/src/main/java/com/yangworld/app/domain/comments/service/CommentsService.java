package com.yangworld.app.domain.comments.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.comments.dto.CommentCreateDto;
import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.question.entity.Comment;

@Primary
public interface CommentsService {
	

	int updateComment(PrincipalDetails principalDetails,  CommentCreateDto commentUpdateDto);

	int deleteComment(PrincipalDetails principalDetails,  CommentCreateDto commentDeleteDto);

	List<Comments> getCommentsByPhotoFeedId(int photoFeedId);


	int insertComment(PrincipalDetails principalDetails, String comment, int photoFeedId);


}
