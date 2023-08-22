package com.yangworld.app.domain.comments.service;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.comments.dto.CommentCreateDto;
import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.comments.repository.CommentsRepository;
import com.yangworld.app.domain.question.entity.Comment;

@Service("FeedCommentsServiceImpl")
public class FeedCommentsServiceImpl implements CommentsService{

	@Autowired
	private CommentsRepository commentsRepository;

    @Override
    public List<Comments> getCommentsByPhotoFeedId(int photoFeedId) {
    	 List<Comments> comments = commentsRepository.getCommentsByPhotoFeedId(photoFeedId);
    	 
        return comments;
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
	public int deleteComment(PrincipalDetails principalDetails, CommentCreateDto commentDeleteDto) {
//		SELECT comment_id, photo_feed_id
//		FROM comment_feed
//		WHERE photo_feed_id = :photoFeedId AND comment_id = :commentId;

		int result = 0;
		
		int commentId = principalDetails.getId();
		
		result = commentsRepository.deleteComment(commentId);
		
		if(result > 0) {
			result = commentsRepository.deleteCommentFeed(commentId);
		}
		
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
}







	





