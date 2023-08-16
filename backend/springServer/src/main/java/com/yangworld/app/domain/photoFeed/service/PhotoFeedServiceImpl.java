package com.yangworld.app.domain.photoFeed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.photoFeed.dto.FeedCreateDto;
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
	public PhotoFeed selectFeed(String nickName) {
		return photoFeedRepository.selectFeed(nickName);
	}



	@Override
	public int deleteFeed(FeedCreateDto feed) {
		// TODO Auto-generated method stub
		return photoFeedRepository.deleteFeed(feed);
	}


}
