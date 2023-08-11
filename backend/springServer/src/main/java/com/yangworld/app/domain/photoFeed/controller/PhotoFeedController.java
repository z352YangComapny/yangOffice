package com.yangworld.app.domain.photoFeed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yangworld.app.domain.photoFeed.service.PhotoPeedService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PhotoFeedController {
	
	@Autowired
	private PhotoPeedService photoPeedService;
	
}
