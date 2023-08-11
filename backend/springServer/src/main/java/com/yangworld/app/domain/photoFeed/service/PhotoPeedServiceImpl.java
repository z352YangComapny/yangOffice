package com.yangworld.app.domain.photoFeed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangworld.app.domain.photoFeed.repository.PhotoPeedRepository;

@Service
public class PhotoPeedServiceImpl implements PhotoPeedService{
	
	@Autowired
	private PhotoPeedRepository photoPeedRepository;

}
