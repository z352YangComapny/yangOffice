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
        // LinkTable delete + commentsTable delete
        // feedId
        try{
            if (writerId == commentWriterId){
                result = commentsRepository.commentDelete(commentId);
                if (result > 0 ){
                    result += commentsRepository.commentFeedDelete(commentId);
                return result;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int commentCreate(PrincipalDetails principalDetails, String content, int feedId) {

        int result = 0;

        int writerId = principalDetails.getId();

        try {
            result = commentsRepository.commentsCreate(writerId, content);
            if (result > 0){
                Comments comments = commentsRepository.commentsIdByWriterIdContent(writerId,content);
                int id = comments.getId();
                result += commentsRepository.commentFeedCreate(id, feedId);
                return result;
            }
        } catch (Exception e){
            e.printStackTrace();
        }


        return 0;
    }
}
