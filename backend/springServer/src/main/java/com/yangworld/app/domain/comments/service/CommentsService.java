package com.yangworld.app.domain.comments.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.comments.dto.CommentAllDto;
import com.yangworld.app.domain.comments.dto.CommentCreateDto;

import com.yangworld.app.domain.comments.dto.QnaCommentCreateDto;

import com.yangworld.app.domain.comments.entity.CommentFeed;

import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.question.entity.Comment;


public interface CommentsService {


	

	int insertComment(PrincipalDetails principalDetails,  CommentCreateDto commentCreateDto);

	


	int updateComment(PrincipalDetails principalDetails,  CommentCreateDto commentUpdateDto);

	List<CommentAllDto> getCommentsByPhotoFeedId(int photoFeedId);

	int insertComment(PrincipalDetails principalDetails, String comment, int photoFeedId);

	int deleteComment(PrincipalDetails principalDetails, int photoFeedId, String comment);



	

	int insertQnaComment(PrincipalDetails principalDetails, QnaCommentCreateDto qnaCommentCreateDto);

	List<Comments> getCommentsByQuestionId(int questionId);




	int deleteComment(PrincipalDetails principalDetails, CommentCreateDto commentDeleteDto);

}
