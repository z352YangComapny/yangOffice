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
        return commentsRepository.getCommentsByPhotoFeedId(photoFeedId);
    }


	@Override
	public int updateComment(PrincipalDetails principalDetails, CommentCreateDto commentUpdateDto) {
		int result = 0;
		
		int writerId = principalDetails.getId();
		String content = commentUpdateDto.getContent();
		
		// 얘는 포토피드 아이디랑 댓글 아이디가 필요함 그럴려면 포토피드 아이디를 받아야겠네뷰딴에서 던져주면 그걸받아서 끌고와서 조회 댓글아이디는 어떻게 조회하게 좋을까?
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







	





