package com.yangworld.app.domain.photoFeed.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.photoFeed.dto.AttachmentPhotoDto;
import com.yangworld.app.domain.photoFeed.dto.FeedCreateDto;
import com.yangworld.app.domain.photoFeed.dto.PhotoAttachmentFeedDto;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import com.yangworld.app.domain.photoFeed.entity.Like;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;
import com.yangworld.app.domain.photoFeed.repository.PhotoFeedRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PhotoFeedServiceImpl implements PhotoFeedService{
	
	@Autowired
	private PhotoFeedRepository photoFeedRepository;

	@Override
	@Transactional
	public int insertFeed(FeedDetails feed) {
		int result = 0;
		
		//TODO 1번 attachment DB Insert
		// 2번 포토 피드 DB Insert
		// 3번 Link Table DB Inserts
		
		//TODO 이 3개의 테이블 인서트가 성공했을 경우에만 커밋 하도록 수정.
		// 1번 파일들을 리스트로 받아
		List<Attachment> attachments = feed.getAttachments();
		
		try {
			if(attachments == null || attachments.isEmpty()) {
				throw new NullPointerException("파일 값이 없습니다");
			} else {
				
				result = photoFeedRepository.insertFeed(feed);
				for(Attachment attach : attachments) {
					attach.setId(feed.getId());
					result = photoFeedRepository.insertAttachment(attach);
					result = photoFeedRepository.insertLink();
				}
			}			
		} catch(Exception e) {
			log.error("Exception Service", e);
		}

		
		return result;
	}

	@Override
	public List<PhotoAttachmentFeedDto> selectFeed(int writerId) {
	    if (writerId < 0) {
	        log.error("username is null");
	        throw new NullPointerException("유저이름이 없습니다.");
	    } else {
	        List<PhotoAttachmentFeedDto> photoFeedList = photoFeedRepository.selectFeed(writerId);
	        
	        log.info("List size: [{}]", photoFeedList.size());
	        
	        for (PhotoAttachmentFeedDto photoFeed : photoFeedList) {
	            List<AttachmentPhotoDto> attachmentPhotoDto = photoFeedRepository.selectAttachmentPhoto(photoFeed.getId());
	            List<Attachment> attachmentList = new ArrayList<>(); // Move this line inside the loop
	            
	            photoFeed.setAttachmentPhotoDto(attachmentPhotoDto);
	            
	            log.info("photo feed check: {}", photoFeed);
	            
	            for (AttachmentPhotoDto attachments : attachmentPhotoDto) {
	                int id = attachments.getAttachmentId();
	                Attachment attachment = photoFeedRepository.selectAttachment(id);
	                
	                attachmentList.add(attachment);
	            }
	            
	            photoFeed.setAttachments(attachmentList);
	            
	            int likeCount = photoFeedRepository.getLikeCount(photoFeed.getId());
	            int commentCount = photoFeedRepository.getCommentCount(photoFeed.getId());
	            
	            photoFeed.setLikeCount(likeCount);
	        }
	        
	        return photoFeedList;
	    }
	}




	@Override
	public int deleteFeed(int feedId) { 
		
		int result = 0;
		
		try {
		result = photoFeedRepository.deleteFeed(feedId);
		result = photoFeedRepository.deleteAttachment(feedId);
		result = photoFeedRepository.deleteLink(feedId);
		}
		catch(Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public int updateFeed(int feedId, String content) {
		int result = 0;
		
		result = photoFeedRepository.updateFeed(feedId, content);
		
		return result;
	}

	@Override
	public PhotoFeed findById(int photoFeedId) {
		return photoFeedRepository.findById(photoFeedId);
	}

	@Override
	public List<Like> getLikesCountByPhotoFeedId(int photoFeedId) {
		return photoFeedRepository.findLikeById(photoFeedId);
	}

	@Override
	public int insertLike(int photoFeedId, int memberId) {
		return photoFeedRepository.insertLike(photoFeedId, memberId);
	}

	@Override
	public int deleteLike(int photoFeedId, int memberId) {
		// TODO Auto-generated method stub
		return photoFeedRepository.deleteLike(photoFeedId, memberId);
	}


}
