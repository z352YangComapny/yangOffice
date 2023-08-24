package com.yangworld.app.domain.photoFeed.service;

import java.util.List;

import javax.validation.Valid;

import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.photoFeed.dto.FeedCreateDto;
import com.yangworld.app.domain.photoFeed.dto.PhotoAttachmentFeedDto;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import com.yangworld.app.domain.photoFeed.entity.Like;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;

public interface PhotoFeedService {

	int insertFeed(FeedDetails feed);

	int deleteFeed(int feedId);

	int updateFeed(int feedId, String content);

	PhotoFeed findById(int photoFeedId);

	List<Like> getLikesCountByPhotoFeedId(int photoFeedId);

	int insertLike(int photoFeedId, int memberId);

	int deleteLike(int photoFeedId, int memberId);


	List<PhotoAttachmentFeedDto> selectFeedDetail(int writerId, int photoFeedId);

	List<PhotoAttachmentFeedDto> selectFeed(int writerId);

	Like getLikeCount(int feedId, int memberId);


	

}
