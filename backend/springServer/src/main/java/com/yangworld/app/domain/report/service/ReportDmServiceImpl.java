package com.yangworld.app.domain.report.service;

import com.yangworld.app.domain.report.dto.ReportDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yangworld.app.domain.report.entity.Report;
import com.yangworld.app.domain.report.entity.ReportDm;
import com.yangworld.app.domain.report.repository.ReportRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@Slf4j
public class ReportDmServiceImpl implements ReportService {

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
			return  o1.getId()- o2.getId();
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
	public int getReportCount() {
		return reportRepository.getReportCount();
	}

//	@Override
//	public int insertReportDm(ReportDm reportDm) {
//		int result = reportRepository.insertReportDm(reportDm);
//		return result;
//	}
}
