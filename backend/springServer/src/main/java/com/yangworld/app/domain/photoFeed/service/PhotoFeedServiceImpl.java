package com.yangworld.app.domain.photoFeed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import com.yangworld.app.domain.photoFeed.repository.PhotoFeedRepository;

@Service
public class PhotoFeedServiceImpl implements PhotoFeedService {
	
	@Autowired
	private PhotoFeedRepository photoPeedRepository;

	@Override
	public int insertPeed(FeedDetails peed) {
		int result = 0;
		
		result = photoPeedRepository.insertPeed(peed);
		
		List<Attachment> attachments = ((FeedDetails) peed).getAttachments();
		if(attachments != null && !attachments.isEmpty()) {
			for(Attachment attach : attachments) {
				attach.setId(peed.getId());
				result = photoPeedRepository.insertAttachment(attach);
			}
		}
		return result;
	}

	@Override
	public int getPhotoFeedTotalCount() {
		int result = photoPeedRepository.getPhotoFeedTotalCount();
		return result;
	}

}
