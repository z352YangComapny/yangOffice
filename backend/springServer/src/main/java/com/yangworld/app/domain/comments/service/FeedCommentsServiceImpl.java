package com.yangworld.app.domain.comments.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.comments.dto.CommentAllDto;
import com.yangworld.app.domain.comments.dto.CommentCreateDto;
import com.yangworld.app.domain.comments.entity.CommentFeed;
import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.comments.repository.CommentsRepository;
import com.yangworld.app.domain.question.entity.Comment;

import lombok.extern.slf4j.Slf4j;

@Service("FeedCommentsServiceImpl")
@Slf4j
public class FeedCommentsServiceImpl implements CommentsService{

	@Autowired
	private CommentsRepository commentsRepository;

    @Override
    public List<CommentAllDto> getCommentsByPhotoFeedId(int photoFeedId) {
    	
		 List<CommentFeed> comments = commentsRepository.getCommentsByPhotoFeedId(photoFeedId);
		 log.info("comments ={}",comments);
		 
		 List<CommentAllDto> commentList = new ArrayList<>();
		 
		 for (CommentFeed comment : comments) {
			 
			 int commentId = comment.getCommentsId();
			 
			 List<Comments> commentreal = commentsRepository.commentByPhotoFeedId(commentId);
			 
			 for(Comments cmt : commentreal ) {
				 
			 
			 CommentAllDto commentAllDto = CommentAllDto.builder()
					 .id(photoFeedId)
					 .writerId(cmt.getWriterId())
					 .content(cmt.getContent())
					 .regDate(cmt.getRegDate())
					 .build();
			 commentList.add(commentAllDto);
			 
			 }
		 }
    	 
        return commentList;
    }


	@Override
	public int updateComment(PrincipalDetails principalDetails, CommentCreateDto commentUpdateDto) {
		int result = 0;
		
		int writerId = principalDetails.getId();
		String content = commentUpdateDto.getContent();
		
		result = commentsRepository.updateComment(writerId, content);
		
		return result;
	}

	@Override
	public int insertComment(PrincipalDetails principalDetails, String comment, int photoFeedId) {
		
		int result = 0;
		
		int writerId = principalDetails.getId();
		
		
		result = commentsRepository.insertComment(writerId, comment);
		
		result = commentsRepository.insertCommentFeed(writerId, photoFeedId);
		
		return result;
	}


	@Override
	public int deleteComment(PrincipalDetails principalDetails, int photoFeedId, String comment) {
		
		int result = 0;
		
		int commentId = principalDetails.getId();
		
		result = commentsRepository.deleteComment(commentId);
		
		if(result > 0) {
			result = commentsRepository.deleteCommentFeed(commentId);
		}
		
		return result;
	}
}







	





