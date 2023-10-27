package com.ssoystory.feedservice.domain.feed.service;

import com.ssoystory.feedservice.domain.feed.entity.PhotoFeed;

import java.util.List;

public interface FeedService {
    List<PhotoFeed> findPhotoFeedsByAuthorAndPageNO();
}
