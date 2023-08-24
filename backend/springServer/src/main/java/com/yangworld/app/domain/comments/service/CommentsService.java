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

import com.yangworld.app.domain.comments.dto.QnaCommentAllDto;

import com.yangworld.app.domain.comments.dto.CommentUpdateDto;
import com.yangworld.app.domain.comments.entity.CommentFeed;

import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.question.entity.Comment;


public interface CommentsService {


	int insertComment(PrincipalDetails principalDetails,  CommentCreateDto commentCreateDto);
	
	List<CommentAllDto> getCommentsByPhotoFeedId(int photoFeedId);
	int insertComment(PrincipalDetails principalDetails, String comment, int photoFeedId);

	int deleteComment(int commentId);

	int updateComment(PrincipalDetails principalDetails, String newContent,int commentId);



	int insertQnaComment(PrincipalDetails principalDetails, QnaCommentAllDto qnaCommentCreateDto);

	List<Comments> getCommentsByQuestionId(int questionId);




	int updateQnaComment(PrincipalDetails principalDetails, QnaCommentAllDto qnaCommentAllDto);


	int deleteQnaComment(int commentId);






	





}
