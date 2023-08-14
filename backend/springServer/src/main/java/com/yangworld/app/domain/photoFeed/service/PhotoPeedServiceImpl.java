package com.yangworld.app.domain.photoFeed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.photoFeed.entity.PeedDetails;
import com.yangworld.app.domain.photoFeed.repository.PhotoFeedRepository;

@Service
public class PhotoPeedServiceImpl implements PhotoPeedService{
	
	@Autowired
	private PhotoFeedRepository photoFeedRepository;

	@Override
	public int insertPeed(PeedDetails peed) {
		int result = 0;
		
		result = photoFeedRepository.insertPeed(peed);
		
		List<Attachment> attachments = ((PeedDetails) peed).getAttachments();
		if(attachments != null && !attachments.isEmpty()) {
			for(Attachment attach : attachments) {
				attach.setId(peed.getId());
				result = photoFeedRepository.insertAttachment(attach);
			}
		}
		return result;
	}

}
