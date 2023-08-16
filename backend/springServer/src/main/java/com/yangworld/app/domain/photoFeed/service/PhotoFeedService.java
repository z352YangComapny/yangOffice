package com.yangworld.app.domain.photoFeed.service;

import javax.validation.Valid;

import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.photoFeed.dto.FeedCreateDto;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;

public interface PhotoFeedService {

	int insertFeed(FeedDetails feed);

	PhotoFeed selectFeed(String nickName);

	

	int deleteFeed(FeedCreateDto feed);

	

}
