package com.ssoystory.feedservice.domain.feed.controller;

import com.ssoystory.feedservice.domain.feed.entity.PhotoFeed;
import com.ssoystory.feedservice.domain.feed.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feed")
public class FeedController {
    @Autowired
    FeedService feedService;
    @GetMapping
    ResponseEntity<List<PhotoFeed>> FeedList (int Author, int pageNo){
        List<PhotoFeed> photoFeeds = feedService.findPhotoFeedsByAuthorAndPageNO();
        return ResponseEntity.ok(photoFeeds);
    }
}
