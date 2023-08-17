package com.yangworld.app.domain.photoFeed.service;

import java.util.List;

import javax.validation.Valid;

import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.photoFeed.dto.FeedCreateDto;
import com.yangworld.app.domain.photoFeed.dto.PhotoAttachmentFeedDto;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;

public interface PhotoFeedService {

	int insertFeed(FeedDetails feed);

	List<PhotoAttachmentFeedDto> selectFeed(int writerId);

	

	int deleteFeed(int feedId);

	

}
