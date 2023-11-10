package com.ssoystory.feedservice.domain.feed.service;

import com.ssoystory.feedservice.domain.feed.entity.PhotoFeed;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface FeedService {
    List<PhotoFeed> findPhotoFeedsByAuthorAndPageNO(String username, int pageNo) throws ExecutionException, InterruptedException;

    void save(String content, List<MultipartFile> upFiles , String authName, Long AuthorId);

    void update(String content, List<MultipartFile> upFiles, String auth, Long authId);
}
