package com.yangworld.app.domain.photoFeed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.photoFeed.dto.FeedCreateDto;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;
import com.yangworld.app.domain.photoFeed.repository.PhotoFeedRepository;

@Service
public class PhotoFeedServiceImpl implements PhotoFeedService{
	
	@Autowired
	private PhotoFeedRepository photoFeedRepository;

	@Override
	public int insertFeed(FeedDetails feed) {
		int result = 0;
		
		
		result = photoFeedRepository.insertFeed(feed);
		
		result = photoFeedRepository.insertLink();
		List<Attachment> attachments = ((FeedDetails) feed).getAttachments();
		if(attachments != null && !attachments.isEmpty()) {
			for(Attachment attach : attachments) {
				attach.setId(feed.getId());
				result = photoFeedRepository.insertAttachment(attach);
			}
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
