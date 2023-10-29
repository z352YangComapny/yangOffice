package com.ssoystory.feedservice.domain.feed.controller;

import com.ssoystory.feedservice.domain.feed.entity.PhotoFeed;
import com.ssoystory.feedservice.domain.feed.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/feed")
public class FeedController {
    @Autowired
    FeedService feedService;
    @GetMapping("/list")
    ResponseEntity<List<PhotoFeed>> FeedList (Long AuthorId, int pageNo){
        List<PhotoFeed> photoFeeds = feedService.findPhotoFeedsByAuthorAndPageNO(AuthorId);
        return ResponseEntity.ok(photoFeeds);
    }
    @PostMapping
    void saveFeed(
            @RequestPart String content,
            @RequestPart(value = "upFile", required = false) List<MultipartFile> upFiles,
            @RequestPart String auth,
            @RequestPart Long authId
    ){
        feedService.save(content, upFiles, auth , authId);
    }
}
