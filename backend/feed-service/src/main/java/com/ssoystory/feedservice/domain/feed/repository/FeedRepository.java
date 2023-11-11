package com.ssoystory.feedservice.domain.feed.repository;

import com.ssoystory.feedservice.domain.feed.entity.PhotoFeed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FeedRepository extends JpaRepository<PhotoFeed,Long> {
//    @Query("SELECT distinct pf FROM PhotoFeed pf LEFT JOIN FETCH pf.Photos p LEFT JOIN FETCH  order by p.id desc")
//    Page<PhotoFeed> findPhotoFeedByAuthorIdOrderByRegDateDesc(Long AuthorId, Pageable pageable);
    @Query("SELECT pf FROM PhotoFeed pf LEFT JOIN FETCH pf.Photos p LEFT JOIN FETCH pf.comments c WHERE pf.authorId = :authorId ORDER BY pf.regDate DESC")
    Page<PhotoFeed> findPhotoFeedByAuthorIdOrderByRegDateDesc(@Param("authorId") Long authorId, Pageable pageable);


}
