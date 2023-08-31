package com.yangworld.app.domain.photoFeed.service;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.photoFeed.dto.FeedDto;
import com.yangworld.app.domain.photoFeed.dto.Like;
import com.yangworld.app.domain.photoFeed.dto.PeedCreateDto;
import com.yangworld.app.domain.photoFeed.dto.PhotoFeedAll;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhotoFeedService {

    int getPhotoFeedTotalCount();

    List<FeedDto> getPhotoFeed(int pageNo, int pageSize);

    int insertfeed(PeedCreateDto _feed, PrincipalDetails member, List<MultipartFile> upFiles) throws IOException;

    List<PhotoFeedAll> findPhotoFeedAll(int id, int feedId);

    int deleteFeed(PrincipalDetails member, int feedId);

    int updateFeed(int feedId, String content, PrincipalDetails member);

    Like likeCheck(int feedId, PrincipalDetails member);
}
