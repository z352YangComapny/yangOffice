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

@Service("FeedCommentsServiceImpl")
public class FeedCommentsServiceImpl implements CommentsService{

	@Autowired
	private CommentsRepository commentsRepository;








	@Override
	public int insertComment(PrincipalDetails principalDetails, CommentCreateDto commentCreateDto) {
	    int result = 0;
	    
	    int writerId = principalDetails.getId();
	    String content = commentCreateDto.getContent();
	    int photoFeedId = commentCreateDto.getPhotoFeed().getId();
	    
	    // 댓글 삽입
	    result = commentsRepository.insertComment(writerId, content);
	    
	    if (result > 0) {
	        // 댓글 피드 삽입
	        int commentId = result; // 이전 삽입에서 얻은 댓글 아이디
	        result = commentsRepository.insertCommentFeed(commentId, photoFeedId);
	    }
	    
	    return result; 
	}


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
		
		int commentId = commentDeleteDto.getId();
		
		result = commentsRepository.deleteComment(commentId);
		
		if(result > 0) {
			result = commentsRepository.deleteCommentFeed(commentId);
		}
		
		return result;
	}
}







	





