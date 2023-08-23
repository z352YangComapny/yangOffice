package com.yangworld.app.domain.comments.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.comments.dto.CommentAllDto;
import com.yangworld.app.domain.comments.dto.CommentCreateDto;
import com.yangworld.app.domain.comments.dto.QnaCommentAllDto;
import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.comments.repository.CommentsRepository;
import com.yangworld.app.domain.profile.service.ProfileServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Primary
@Service("QnACommentsServiceImpl")
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class QnACommentsServiceImpl implements CommentsService{

	
	@Autowired
	CommentsRepository commentsRepository;

	@Override
	public int insertComment(PrincipalDetails principalDetails, CommentCreateDto commentCreateDto) {return 0;}
	@Override
	public int updateComment(PrincipalDetails principalDetails, CommentCreateDto commentUpdateDto) {return 0;}
	@Override
	public int deleteComment(PrincipalDetails principalDetails, CommentCreateDto commentDeleteDto) {return 0;}
	@Override
	public List<CommentAllDto> getCommentsByPhotoFeedId(int photoFeedId) {return null;}
	@Override
	public int insertComment(PrincipalDetails principalDetails, String comment, int photoFeedId) {
		return 0;
	}
	@Override
	public int deleteComment(PrincipalDetails principalDetails, int photoFeedId, String comment) {
		return 0;
	}
	
	
	@Override
	public int insertQnaComment(PrincipalDetails principalDetails, QnaCommentAllDto qnaCommentCreateDto) {
		int result = 0;
	    
	    int writerId = principalDetails.getId();
	    String content = qnaCommentCreateDto.getContent();
	    int questionId = qnaCommentCreateDto.getCommentQna().getQuestionId();
	    
	    log.info("questionId = {}", questionId);
	    
	    result = commentsRepository.insertComment(writerId, content);
	    
	    if (result > 0) {
	    	
	        int commentId = result; 
	        result = commentsRepository.insertCommentQna(commentId, questionId);
	    }
	    
	    return result; 
	}
	@Override
	public List<Comments> getCommentsByQuestionId(int questionId) {
		
		return commentsRepository.getCommentsByQuestionId(questionId);
	}
	@Override
	public int updateQnaComment(PrincipalDetails principalDetails, QnaCommentAllDto qnaCommentUpdateDto) {
		 int result = 0;
		 
		 int writerId = principalDetails.getId();
		 String content = qnaCommentUpdateDto.getContent();
		 int questionId = qnaCommentUpdateDto.getCommentQna().getQuestionId();
		 
		 log.info("writherId = {}", writerId);
		 log.info("updateContent = {}", content);
		 log.info("updateQuestionId = {}", questionId);
		 
		 result = commentsRepository.updateCommentQna(questionId, content);
	
		 
		 return result;
	}
	
}
