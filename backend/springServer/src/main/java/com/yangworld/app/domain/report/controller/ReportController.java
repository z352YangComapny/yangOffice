package com.yangworld.app.domain.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.report.dto.ReportCreateDto;
import com.yangworld.app.domain.report.entity.Report;
import com.yangworld.app.domain.report.entity.ReportDm;
import com.yangworld.app.domain.report.service.ReportService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	// insert 하실때 .insertReport 사용하시면 insert 됩니다. !!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	/**
	 * 윤아
	 * dm report 인서트 
	 */
	@PostMapping("/insertReportDm")
	private ResponseEntity<?> insertReportDm(@AuthenticationPrincipal PrincipalDetails principal	, @RequestBody ReportCreateDto _reportDto, @RequestParam int dmId) {
		
		int reporterId = principal.getId();
		
		Report report = _reportDto.toReport();
		log.info("report = {}", report);
		report.setReporterId(reporterId);
		
		// 1. report 테이블에 insert
		reportService.insertReportDm(report, dmId); // reportId = report 시퀀스값
		
		return ResponseEntity.ok().build();
	}

	@PostMapping("/insertReportGuestBook.do")
	public ResponseEntity<?> insertReportGuestBook(
			@AuthenticationPrincipal PrincipalDetails principalDetails,
			ReportCreateDto _reportDto,
			@RequestParam int guestBookId
		){
		
		int reporterId = principalDetails.getId();
		
		Report report = _reportDto.toReport();
		log.info("report = {}",report);
		report.setReporterId(reporterId);
		
		
		reportService.insertReportGuestBook(report,guestBookId);
		
		return ResponseEntity.ok().build();
	}
	
	
	
	
}
