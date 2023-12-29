package com.yangworld.app.domain.comments.service;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

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
	@Override
	public int deleteQnaComment(int commentId) {
		log.info("commentId = {}", commentId);
		return commentsRepository.deleteCommentQna(commentId);
		
	}

	@Override
	public Comments findCommentById(int commentsId) {
		return null;
	}

	@Override
	public int commentsUpdate(PrincipalDetails principalDetails, int commentId, String content) {
		return 0;
	}

	@Override
	public int commentsDelete(PrincipalDetails principalDetails, int commentId) {
		return 0;
	}

	@Override
	public int commentCreate(PrincipalDetails principalDetails, String content, int feedId) {
		return 0;
	}


	@Override
	public int deleteComment(int commentId) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int updateComment(PrincipalDetails principalDetails, String newContent, int commentId) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int insertComment(PrincipalDetails principalDetails, CommentCreateDto commentCreateDto) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<CommentAllDto> getCommentsByPhotoFeedId(int photoFeedId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int insertComment(PrincipalDetails principalDetails, String comment, int photoFeedId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
