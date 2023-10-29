package com.ssoystory.feedservice.domain.feed.repository;

import com.ssoystory.feedservice.domain.feed.entity.PhotoFeed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepository extends JpaRepository<PhotoFeed,Long> {

    List<PhotoFeed> findPhotoFeedByAuthorIdOrderByRegDateDesc(Long AuthorId);
}
