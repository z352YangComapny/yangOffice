package com.ssoystory.feedservice.domain.feed.repository;

import com.ssoystory.feedservice.domain.feed.entity.PhotoFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FeedRepository extends JpaRepository<PhotoFeed,Long> {
    List<PhotoFeed> findPhotoFeedByAuthorIdOrderByRegDateDesc(Long AuthorId, int pageNo);
}
