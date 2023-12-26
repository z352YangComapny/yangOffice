package com.yangworld.app.domain.report.service;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;
import com.yangworld.app.domain.report.dto.ReportCreateDto;
import com.yangworld.app.domain.report.dto.ReportDetailDto;
import com.yangworld.app.domain.report.dto.ReportFeedRequest;
import com.yangworld.app.domain.report.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yangworld.app.domain.report.repository.ReportRepository;

import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.sql.SQLTransactionRollbackException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
//@Transactional(rollbackFor = Exception.class)
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepository reportRepository;
	
	 @Override
	    @Transactional
	    public int insertReportDm(Report report, int dmId) {
	        int result = reportRepository.insertReport(report);
	        
	        int reportId = report.getId(); // report 테이블의 id값 가져오기
	        
	        // 2. report Dm 테이블에 insert
	        ReportDm reportDm = ReportDm.builder()
	                                     .id(reportId)
	                                     .dmId(dmId).build();
	        
	        System.out.println(reportDm.toString());
	        log.info("reportDm={}", reportDm);
	        
	        int resultReportDm = reportRepository.insertReportDm(reportDm);
	        
	        return resultReportDm; // insertReportDm 메서드의 결과 반환
	    }

	@Override
	public int insertReportFeed(PrincipalDetails principalDetails, ReportFeedRequest feedRequest) {

			int result = 0;

			int reporterId = principalDetails.getId();
			int feedId = feedRequest.getFeedId();
			String content = feedRequest.getContent();

			PhotoFeed photoFeed = reportRepository.findByFeedId(feedId);
			int reportedId = photoFeed.getWriterId();

			Report report = Report.builder()
					.reportedId(reportedId)
					.reporterId(reporterId)
					.content(content)
					.build();

			try {
				result = reportRepository.insertReport(report);
				if (result > 0) {
					result += reportRepository.insertReportFeed(feedId);
					if(result > 1){
						return result;
					}
				}
			}catch (Exception e){
				e.printStackTrace();
				log.debug("안돼..");
			}
			return 0;
	}

	@Override
	public int insertReportFeedComments(PrincipalDetails principalDetails, ReportFeedRequest feedRequest) {

		int result = 0;

		int reporterId = principalDetails.getId();
		int CommentsId = feedRequest.getCommentsId();
		String content = feedRequest.getContent();

		PhotoFeed Comments = reportRepository.findByCommentsId(CommentsId);
		int reportedId = Comments.getWriterId();

		Report report = Report.builder()
				.reportedId(reportedId)
				.reporterId(reporterId)
				.content(content)
				.build();
		try {
			result = reportRepository.insertReport(report);
			if (result > 0) {
				result += reportRepository.insertReportFeedComments(CommentsId);
				return result;
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("진짜 안돼");
		}

		 return 0;
	}

////////////////////////////////////////////////////////////

	@Override
	public int getReportCount() {
		return reportRepository.getReportCount();
	}



	@Override
	public List<ReportDetailDto> getAllReports(int pageNo, int pageSize) {
		List<ReportDetailDto> reportTotalList = new ArrayList<>();
		// 포토피드
		List<ReportDetailDto> reportPhotofeeds = reportRepository.getPotofeedReport();
		for(ReportDetailDto reportFeed : reportPhotofeeds){
			reportFeed.setType("PhotoFeed");
			reportTotalList.add(reportFeed);
		}

		// 스토리
		List<ReportDetailDto> reportStoryList = reportRepository.getReportStory();
		for(ReportDetailDto reportStory : reportStoryList){
			reportStory.setType("Story");
			reportTotalList.add(reportStory);
		}

		// DM
		List<ReportDetailDto> reportDmList = reportRepository.getReportDm();
		for(ReportDetailDto reportDM : reportDmList){
			reportDM.setType("DM");
			reportTotalList.add(reportDM);
		}

		// 댓글
		List<ReportDetailDto> reportCmtList = reportRepository.getReportCmt();
		for(ReportDetailDto reportCmt : reportCmtList){
			reportCmt.setType("Comment");
			reportTotalList.add(reportCmt);

		}

		//방명록
		List<ReportDetailDto> reportGuestBookList = reportRepository.getReportGuestBook();
		for(ReportDetailDto reportGuestBook : reportGuestBookList){
			reportGuestBook.setType("GuestBook");
			reportTotalList.add(reportGuestBook);
		}
		reportTotalList.sort((o1, o2) -> {
			return  -(o1.getId()- o2.getId());
		});

		int start = (pageNo-1)* pageSize;
		if(start >= reportTotalList.size()){
			return new ArrayList<>();
		}
		int end = Math.min(start + pageSize, reportTotalList.size());
		List<ReportDetailDto> result = reportTotalList.subList(start,end);
		log.info("{}",result);

		return result;
	}

	@Override
	public int insertReportProfile(Report report, int profileId) {

		int result = reportRepository.insertReport(report);
		int reportId = report.getId();
		ReportProfile reportProfile = ReportProfile.builder()
				.id(reportId)
				.profileId(profileId)
				.build();
		int resultReportProfile = reportRepository.insertReportProfile(reportProfile);

		return resultReportProfile;


	}
	/*
	 * GuestBook
	 * */
	@Override
	public int insertReportGuestBook(Report report, int guestbookId) {
		int result = reportRepository.insertReport(report);
		int reportId = report.getId();
		log.info("reportId@guest = {}", reportId);

		ReportGuestBook reportGuestBook = ReportGuestBook.builder()
				.id(reportId)
				.guestBookId(guestbookId)
				.build();

		int resultReportGuestBook= reportRepository.insertReportGuestBook(reportGuestBook);

		return resultReportGuestBook;
	}

	@Override
	public int insertReportPhotoFeed(Report report, int feedId) {

		int result = reportRepository.insertReport(report);
		int reportId = report.getId();
		ReportPhotoFeed reportFeed = ReportPhotoFeed.builder()
				.id(reportId)
				.photoFeedId(feedId)
				.build();
		int feedReport = reportRepository.insertReportPhotoFeed(reportFeed);


		return feedReport;
	}

	@Override
	public int insertReportComments(Report report, int commentsId) {
		int result = reportRepository.insertReport(report);
		int reportId = report.getId();
		ReportCommentsFeed reportCommentsFeed = ReportCommentsFeed.builder()
				.id(reportId)
				.commentsId(commentsId)
				.build();
		int commentsReport = reportRepository.insertReportComments(reportCommentsFeed);


		return commentsReport;
	}

	@Override
	public int insertReportStory(Report report, int storyId) {
		int result =  reportRepository.insertReport(report);
		int reportId =report.getId();

		ReportStory reportStory = ReportStory.builder()
				.id(reportId)
				.storyId(storyId)
				.build();

		int resultReportStory = reportRepository.insertReportStoryReport(reportStory);

		return resultReportStory;
	}



}
