package com.yangworld.app.domain.comments.service;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.comments.repository.CommentsRepository;
import com.yangworld.app.domain.photoFeed.dto.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("FeedCommentsServiceImpl")
@Slf4j
public class FeedCommentsServiceImpl implements CommentsService{

    @Autowired
    CommentsRepository commentsRepository;

    @Override
    public int commentsUpdate(PrincipalDetails principalDetails, int commentId, String content) {

        int result = 0;
        int writerId = principalDetails.getId();

        Comment comment = commentsRepository.commentByCommentId(commentId);
        int commentWriterId = comment.getWriterId();
        try{
            if (writerId == commentWriterId){
                result = commentsRepository.commentUpdate(commentId, content);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        return result;
    }

    @Override
    public int commentsDelete(PrincipalDetails principalDetails, int commentId) {
        int result = 0;
        int writerId = principalDetails.getId();

        Comment comment = commentsRepository.commentByCommentId(commentId);
        int commentWriterId = comment.getWriterId();
        // feedId
        try{
            if (writerId == commentWriterId){
//                result = commentsRepository.commentDelete(commentId);
                return result;
            }
        }catch (Exception e){

        }

        return 0;
    }
}
