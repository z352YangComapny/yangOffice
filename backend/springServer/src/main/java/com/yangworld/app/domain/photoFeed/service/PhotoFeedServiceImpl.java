package com.yangworld.app.domain.photoFeed.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.yangworld.app.commons.HelloSpringUtils;
import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.attachment.entity.AttachmentPhotoFeed;
import com.yangworld.app.domain.attachment.entity.AttachmentProfile;
import com.yangworld.app.domain.attachment.repository.AttachmentRepository;
import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.comments.repository.CommentsRepository;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.repository.MemberRepository;
import com.yangworld.app.domain.photoFeed.dto.*;
import com.yangworld.app.domain.photoFeed.entity.CommentFeed;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.photoFeed.entity.FeedDetails;
import com.yangworld.app.domain.photoFeed.repository.PhotoFeedRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class PhotoFeedServiceImpl implements PhotoFeedService {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PhotoFeedRepository photoFeedRepository;
	@Autowired
	private AttachmentRepository attachmentRepository;


	@Autowired
	private CommentsRepository commentsRepository;

//	@Override
//	public int insertfeed(FeedDetails peed) {
//		int result = 0;
//
//		result = photofeedRepository.insertfeed(peed);
//
//		List<Attachment> attachments = ((FeedDetails) peed).getAttachments();
//		if(attachments != null && !attachments.isEmpty()) {
//			for(Attachment attach : attachments) {
//				attach.setId(peed.getId());
//				result = photofeedRepository.insertAttachment(attach);
//			}
//		}
//		return result;
//	}

	@Override
	@Transactional
	public int getPhotoFeedTotalCount() {
		int result = photoFeedRepository.getPhotoFeedTotalCount();
		return result;
	}

	@Override
	public List<FeedDto> getPhotoFeed(int pageNo, int pageSize) {
		int offset = (pageNo-1)*pageSize;
		RowBounds rowBounds = new RowBounds(offset, pageSize);
		List<PhotoFeed> result = photoFeedRepository.getPhotoRawFeed(rowBounds);
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

	@Override
	@Transactional
	public int insertfeed(PeedCreateDto _feed, PrincipalDetails member, List<MultipartFile> upFiles) throws IOException {

		int result = 0;

		List<Attachment> attachments = new ArrayList<>();
		log.info("upFiles = {}",upFiles);
		for(MultipartFile upFile : upFiles){
			if(!upFile.isEmpty()) {
				String originalFilename = upFile.getOriginalFilename();
				String renamedFilename = HelloSpringUtils.getRenameFilename(originalFilename);
				File destFile = new File(renamedFilename);
				upFile.transferTo(destFile);


				Attachment attach =
						Attachment.builder()
								.originalFilename(originalFilename)
								.renamedFilename(renamedFilename)
								.build();
				attachments.add(attach);
			}
		}

		FeedDetails feed = FeedDetails.builder()
				.writerId(member.getId())
				.content(_feed.getContent())
				.attachments(attachments)
				.build();


		try {
			if(attachments.isEmpty()) {
				throw new NullPointerException("파일 값이 없습니다");
			} else {

				result = photoFeedRepository.insertFeed(feed);
				for(Attachment attach : attachments) {
					attach.setId(feed.getId());
					result = photoFeedRepository.insertAttachment(attach);
					result = photoFeedRepository.insertLink();
				}
			}
		} catch(Exception e) {
			log.error("Exception Service", e);
		}


		return result;
	}

	@Override
	public List<PhotoFeedAll> findPhotoFeedAll(int id, int feedId) {
		if (feedId < 0) {
			log.error("photoFeedId is null");
			throw new IllegalArgumentException("피드 ID가 유효하지 않습니다.");
		}

		// 사진 시작
		List<PhotoFeedAll> feedDetails = photoFeedRepository.findAllFeedByWriterId(id);

		for (PhotoFeedAll feedAll : feedDetails) {
			int photoFeedId = feedAll.getId();

			List<AttachmentPhotoDto> attachmentPhotoFeed = photoFeedRepository.findAttachmentPhotoFeedByPhotoFeedId(photoFeedId);

			// 초기화
			feedAll.setAttachments(new ArrayList<>());

			for (AttachmentPhotoDto attachments : attachmentPhotoFeed) {
				int attachmentId = attachments.getAttachmentId();
				List<Attachment> attachment = photoFeedRepository.findAttachmentsById(attachmentId);

				for (Attachment att : attachment) {
					if (att != null) {
						AttachmentDto attachmentDto = new AttachmentDto();
						attachmentDto.setRenamedFilename(att.getRenamedFilename());

						feedAll.getAttachments().add(attachmentDto);
					}
				}
			}
			// 사진 끝

			List<CommentFeed> comments = commentsRepository.getCommentsByPhotoFeedId(photoFeedId);

			List<CommenttDto> commentList = new ArrayList<>();

			for (CommentFeed comment : comments) {
				int commentId = comment.getCommentsId();

				List<Comments> commentreal = commentsRepository.commentByPhotoFeedId(commentId);

				for (Comments cmt : commentreal) {
					Comments member = commentsRepository.nickNameByCommentsId(cmt.getId());

					CommenttDto commenttDto = new CommenttDto();
					commenttDto.setContent(cmt.getContent());
					commenttDto.setNickName(member.getNickName());

					commentList.add(commenttDto);
				}
			}
			int likeCount = photoFeedRepository.getLikeCount(photoFeedId);
			int commentCount = commentList.size();

			feedAll.setLikeCount(likeCount);
			feedAll.setCommentCount(commentCount);

			feedAll.setComments(commentList);
		}

		return feedDetails;
	}

	@Transactional
	@Override
	public int deleteFeed(PrincipalDetails member, int feedId) {

		int result = 0;

		int memberId = member.getId();

		PhotoFeed photoFeed = photoFeedRepository.findById(memberId);

		int writerId = photoFeed.getWriterId();

		if (memberId == writerId){

			try {
				result = photoFeedRepository.deleteFeed(feedId);
				result += photoFeedRepository.deleteAttachment(feedId);
				result += photoFeedRepository.deleteLink(feedId);
				return result;

			} catch (Exception e) {
				throw e;
			}

		}

		return result;
	}

	@Override
	public int updateFeed(int feedId, String content, PrincipalDetails member) {

		int result = 0;

		int memberId = member.getId();

		PhotoFeed photoFeed = photoFeedRepository.findById(memberId);

		int writerId = photoFeed.getWriterId();

		if (writerId == memberId) {

			try {
				result = photoFeedRepository.updateFeed(feedId, content);
				return result;
			}catch (Exception e){
				throw e;
			}

		}
		return result;
	}

	@Override
	public Like likeCheck(int feedId, PrincipalDetails member) {
		int memberId = member.getId();

		Like likeCheck = photoFeedRepository.likeCheck(feedId,memberId);

		if (likeCheck != null) {
			int result = photoFeedRepository.deleteLike(feedId, memberId);
		} else {
			int result = photoFeedRepository.insertLike(feedId, memberId);
		}
		return likeCheck;
	}


}
