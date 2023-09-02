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
import com.yangworld.app.domain.comments.dto.QnaCommentAllDto;
import com.yangworld.app.domain.comments.dto.CommentUpdateDto;
import com.yangworld.app.domain.comments.entity.CommentFeed;
import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.comments.repository.CommentsRepository;
import com.yangworld.app.domain.member.entity.Member;

import lombok.extern.slf4j.Slf4j;
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
	private CommentsRepository commentsRepository;


	@Override
    public List<CommentAllDto> getCommentsByPhotoFeedId(int photoFeedId) {
    	
		 List<CommentFeed> comments = commentsRepository.getCommentsByPhotoFeedId(photoFeedId);
		 
		 List<CommentAllDto> commentList = new ArrayList<>();
//		 List<Member> member = commentsRepository.getMemberId(photoFeedId);
		 for (CommentFeed comment : comments) {
			 
			 int commentId = comment.getCommentsId();
			 
			 log.info("commentId = {}", commentId);
			 
			 List<Comments> commentreal = commentsRepository.commentByPhotoFeedId(commentId);
			 // commentreal = CommentFeed테이블 조회 결과를 가지고 comment테이블 조회
			 // commentreal 결과 = 12
			 for(Comments cmt : commentreal) {
				 
				 log.info("cmt = {}",cmt.getWriterId());
				 // commentreal 결과값에 writerId가지고 memberNickname을 조회
				 
			 	Comments member = commentsRepository.nickNameByCommentsId(cmt.getId());
				 
					 // builder
					 // 하지만 이렇게 builder를 해준다면 한댓글이 댓글이 여러개가 나옴
					 CommentAllDto commentAllDto = CommentAllDto.builder()
							 .id(cmt.getId())
							 .writerId(cmt.getWriterId())
							 .nickName(member.getNickName())
							 .content(cmt.getContent())
							 .regDate(cmt.getRegDate())
							 .build();
					 
				 
					 commentList.add(commentAllDto);
			 }
		 }
    	 
        return commentList;
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
	public int deleteComment(int commentId) {
		
		int result = 0;
		
		log.info("commentId ={}", commentId);
		result = commentsRepository.deleteComment(commentId);
		
		if(result > 0) {
			result = commentsRepository.deleteCommentFeed(commentId);
		}
		
		return result;
	}
	@Override
	public int updateComment(PrincipalDetails principalDetails, String newContent, int commentId) {

		int result = 0;

		result = commentsRepository.updateComment(commentId, newContent); 

		
		return result;
	}
	
	
	
	
	
	@Override
	public int insertComment(PrincipalDetails principalDetails, CommentCreateDto commentCreateDto) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int insertQnaComment(PrincipalDetails principalDetails, QnaCommentAllDto qnaCommentCreateDto) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Comments> getCommentsByQuestionId(int questionId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int updateQnaComment(PrincipalDetails principalDetails, QnaCommentAllDto qnaCommentAllDto) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int deleteQnaComment(int commentId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Comments findCommentById(int commentsId) {
		return commentsRepository.findCommentById(commentsId);
	}


    @Override
    public int commentsUpdate(PrincipalDetails principalDetails, int commentId, String content) {

        int result = 0;
        int writerId = principalDetails.getId();

        Comments comment = commentsRepository.commentByCommentId(commentId);
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

        Comments comment = commentsRepository.commentByCommentId(commentId);
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

