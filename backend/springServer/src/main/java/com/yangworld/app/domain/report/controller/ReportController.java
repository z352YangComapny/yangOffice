package com.yangworld.app.domain.report.controller;

import com.yangworld.app.domain.report.dto.ReportFeedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
	/**
	 * Patch : http://localhost:8080/report/insertReportFeed
	 * raw/json
	 * {
	 *   "feedId": 30,
	 *   "content": "이 사진에 불쾌한 내용이 포함되어 있습니다."
	 * }
	 * - Headers : Authorization ** 필수
	 */
	@PatchMapping("/insertReportFeed")
	public ResponseEntity<?> insertReportFeed(
		@AuthenticationPrincipal PrincipalDetails principalDetails,
		@RequestBody ReportFeedRequest feedRequest
    ){
		int result = reportService.insertReportFeed(principalDetails, feedRequest);
	return ResponseEntity.ok().body(result);
	}

	/**
	 * Patch : http://localhost:8080/report/insertReportFeedComments
	 * raw/json
	 * {
	 *   "commentsId": 20,
	 *   "content": "이 댓글에 불쾌한 내용이 포함되어 있습니다."
	 * }
	 * - Headers : Authorization ** 필수
	 */
	@PatchMapping("/insertReportFeedComments")
	public ResponseEntity<?> inserReportFeedComments(
			@AuthenticationPrincipal PrincipalDetails principalDetails,
			@RequestBody ReportFeedRequest feedRequest
	){
		int result = reportService.insertReportFeedComments(principalDetails, feedRequest);

		return ResponseEntity.ok().body(result);
	}

	
	
	
	
}
