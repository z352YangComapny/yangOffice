package com.yangworld.app.domain.photoFeed.service;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.photoFeed.dto.FeedDto;
import com.yangworld.app.domain.photoFeed.dto.PhotoFeedDailyDto;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;
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


    PhotoFeed findPhotoFeedById(int feedId);

    List<PhotoFeedDailyDto> findPhotoFeedDaily();

    int insertfeed(String content, PrincipalDetails member, List<MultipartFile> upFiles) throws IOException;

    List<PhotoFeedAll> findPhotoFeedAll(String userName);

    int deleteFeed(PrincipalDetails member, int feedId);

    int updateFeed(int feedId, String content, PrincipalDetails member);

    Like likeCheck(int feedId, PrincipalDetails member);

}