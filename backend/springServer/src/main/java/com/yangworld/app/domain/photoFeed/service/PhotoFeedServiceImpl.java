package com.yangworld.app.domain.photoFeed.service;

import java.util.ArrayList;
import java.util.List;

import com.yangworld.app.domain.attachment.entity.AttachmentPhotoFeed;
import com.yangworld.app.domain.attachment.repository.AttachmentRepository;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.repository.MemberRepository;
import com.yangworld.app.domain.photoFeed.dto.FeedDto;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import com.yangworld.app.domain.photoFeed.repository.PhotoFeedRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class PhotoFeedServiceImpl implements PhotoFeedService {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PhotoFeedRepository photoPeedRepository;
	@Autowired
	private AttachmentRepository attachmentRepository;

	@Override
	public int insertPeed(FeedDetails peed) {
		int result = 0;

		result = photoPeedRepository.insertPeed(peed);

		List<Attachment> attachments = ((FeedDetails) peed).getAttachments();
		if(attachments != null && !attachments.isEmpty()) {
			for(Attachment attach : attachments) {
				attach.setId(peed.getId());
				result = photoPeedRepository.insertAttachment(attach);
			}
		}
		return result;
	}

	@Override
	@Transactional
	public int getPhotoFeedTotalCount() {
		int result = photoPeedRepository.getPhotoFeedTotalCount();
		return result;
	}

	@Override
	public List<FeedDto> getPhotoFeed(int pageNo, int pageSize) {
		int offset = (pageNo-1)*pageSize;
		RowBounds rowBounds = new RowBounds(offset, pageSize);
		List<PhotoFeed> result = photoPeedRepository.getPhotoRawFeed(rowBounds);
		List<FeedDto> feedDtos = new ArrayList<>();
		for (PhotoFeed feed: result ) {
			List<String> attachmentNames = new ArrayList<>();
			Member member = memberRepository.findById(feed.getWriterId());
			List<AttachmentPhotoFeed> attachmentPhotoFeeds = attachmentRepository.findAttachmentByFeedId(feed.getId());
			if(attachmentPhotoFeeds == null){
				throw new NullPointerException("첨부사진 찾을 수 없음");
			}
			for(AttachmentPhotoFeed attachmentPhotoFeed :attachmentPhotoFeeds){
				attachmentNames.add(attachmentPhotoFeed.getRenamedFilename());
			}

			FeedDto feedDto = FeedDto.builder()
					.id(feed.getId())
					.username(member.getUsername())
					.content(feed.getContent())
					.regDate(feed.getRegDate())
					.attachmentNames(attachmentNames)
					.build();

			feedDtos.add(feedDto);
		}
		return feedDtos;
	}

}
