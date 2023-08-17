package com.yangworld.app.domain.comments.service;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.comments.dto.CommentCreateDto;
import com.yangworld.app.domain.comments.repository.CommentsRepository;

@Service("FeedCommentsServiceImpl")
public class FeedCommentsServiceImpl implements CommentsService{

	@Autowired
	private CommentsRepository commentsRepository;








	@Override
	public int insertComment(PrincipalDetails principalDetails,  CommentCreateDto commentCreateDto) {
		int result = 0;
		// TODO 검사
		int writerId = principalDetails.getId();
		String content = commentCreateDto.getContent();
		 result = commentsRepository.insertComment(writerId, content);
		 result = commentsRepository.insertCommnetFeed();
		 
        
		return result; 
	}
}







	





