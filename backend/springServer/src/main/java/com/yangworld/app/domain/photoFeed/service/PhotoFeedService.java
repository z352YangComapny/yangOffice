package com.yangworld.app.domain.photoFeed.service;

import com.yangworld.app.domain.photoFeed.dto.FeedDto;
import com.yangworld.app.domain.photoFeed.dto.PhotoFeedDailyDto;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;

import java.util.List;

public interface PhotoFeedService {

	int insertPeed(FeedDetails peed);

    int getPhotoFeedTotalCount();

    List<FeedDto> getPhotoFeed(int pageNo, int pageSize);

    PhotoFeed findPhotoFeedById(int feedId);

    List<PhotoFeedDailyDto> findPhotoFeedDaily();
}
