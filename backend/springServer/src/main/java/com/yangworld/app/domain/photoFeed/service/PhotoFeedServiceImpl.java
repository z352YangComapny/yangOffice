package com.yangworld.app.domain.photoFeed.service;

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
				for(Attachment attach : attachments) {
					attach.setId(feed.getId());
					result = photoFeedRepository.insertAttachment(attach);
					log.info("insert attachment result : {}", result);
				}
				result = photoFeedRepository.insertFeed(feed);
				log.info("insert photofeed result : {}", result);
				result = photoFeedRepository.insertLink();
				log.info("insert link result : {}", result);
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
			
			log.info("List size : [{}]", photoFeedList.size());
			//회원 아이디 받아와서 피드가 몇 개인지 조회.
			for(PhotoAttachmentFeedDto photoFeed : photoFeedList) {
				AttachmentPhotoDto attachmentPhotoDto = photoFeedRepository.selectAttachmentPhoto(photoFeed.getId());
				
				log.info("photo feed one : {}", photoFeed);
				photoFeed.setAttachmentPhotoDto(attachmentPhotoDto);
				log.info("photo feed check : {}", photoFeed);
				
				int id = attachmentPhotoDto.getAttachmentId();
				List<Attachment> attachment = photoFeedRepository.selectAttachment(id);
				photoFeed.setAttachments(attachment);
				
				
			}
			
			return photoFeedList;
		}
	}



	@Override
	public int deleteFeed(FeedCreateDto feed) {
		// TODO Auto-generated method stub
		return photoFeedRepository.deleteFeed(feed);
	}


}
