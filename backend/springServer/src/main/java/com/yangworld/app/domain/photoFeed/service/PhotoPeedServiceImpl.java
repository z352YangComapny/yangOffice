package com.yangworld.app.domain.photoFeed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.photoFeed.entity.PeedDetails;
import com.yangworld.app.domain.photoFeed.repository.PhotoPeedRepository;

@Service
public class PhotoPeedServiceImpl implements PhotoPeedService{
	
	@Autowired
	private PhotoPeedRepository photoPeedRepository;

	@Override
	public int insertPeed(PeedDetails peed) {
		int result = 0;
		
		result = photoPeedRepository.insertPeed(peed);
		
		List<Attachment> attachments = ((PeedDetails) peed).getAttachments();
		if(attachments != null && !attachments.isEmpty()) {
			for(Attachment attach : attachments) {
				attach.setId(peed.getId());
				result = photoPeedRepository.insertAttachment(attach);
			}
		}
		return result;
	}

}
