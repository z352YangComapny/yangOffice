package com.yangworld.app.domain.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yangworld.app.domain.report.entity.Report;
import com.yangworld.app.domain.report.entity.ReportDm;
import com.yangworld.app.domain.report.entity.ReportGuestBook;

import com.yangworld.app.domain.report.entity.ReportProfile;

import com.yangworld.app.domain.report.repository.ReportRepository;

import lombok.extern.slf4j.Slf4j;

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
	public int insertReportGuestBook(Report report, int guestBookId) {
		int reportId = report.getId();
		
		ReportGuestBook reportGuestBook = ReportGuestBook.builder()
											.id(reportId)
											.guestBookId(guestBookId)
											.build();
		return reportRepository.insertReportGuestBook(reportGuestBook);
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

	
//	@Override
//	public int insertReportDm(ReportDm reportDm) {
//		int result = reportRepository.insertReportDm(reportDm);
//		return result;
//	}
}
