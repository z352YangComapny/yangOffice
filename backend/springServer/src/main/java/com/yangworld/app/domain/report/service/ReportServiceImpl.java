package com.yangworld.app.domain.report.service;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;
import com.yangworld.app.domain.report.dto.ReportCreateDto;
import com.yangworld.app.domain.report.dto.ReportFeedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yangworld.app.domain.report.entity.Report;
import com.yangworld.app.domain.report.entity.ReportDm;
import com.yangworld.app.domain.report.repository.ReportRepository;

import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.sql.SQLTransactionRollbackException;

@Service
@Slf4j
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

//	@Override
//	public int insertReportDm(ReportDm reportDm) {
//		int result = reportRepository.insertReportDm(reportDm);
//		return result;
//	}
}
