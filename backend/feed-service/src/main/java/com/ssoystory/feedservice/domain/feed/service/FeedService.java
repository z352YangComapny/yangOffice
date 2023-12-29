package com.ssoystory.feedservice.domain.feed.service;

import com.ssoystory.feedservice.domain.feed.entity.PhotoFeed;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface FeedService {
    List<PhotoFeed> findPhotoFeedsByAuthorAndPageNO(String username, Pageable pageable) throws ExecutionException, InterruptedException;

    void save(String content, List<MultipartFile> upFiles , String authName, Long AuthorId);

    void update(long id, String content, Long authId);

    void delete(long id, Long authId);
}
