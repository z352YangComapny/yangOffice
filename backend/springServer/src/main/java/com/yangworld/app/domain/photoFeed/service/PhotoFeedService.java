package com.yangworld.app.domain.photoFeed.service;

import com.yangworld.app.domain.photoFeed.entity.FeedDetails;

public interface PhotoFeedService {

	int insertPeed(FeedDetails peed);

    int getPhotoFeedTotalCount();
}
