package com.ssoystory.feedservice.domain.feed.service;

import com.ssoystory.feedservice.domain.feed.entity.PhotoFeed;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FeedService {
    List<PhotoFeed> findPhotoFeedsByAuthorAndPageNO(Long AuthorId);

    void save(String content, List<MultipartFile> upFiles , String authName, Long AuthorId);
}
