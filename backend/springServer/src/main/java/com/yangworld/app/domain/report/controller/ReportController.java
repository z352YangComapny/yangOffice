package com.yangworld.app.domain.report.controller;

import com.yangworld.app.domain.report.dto.ReportDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import java.util.List;
import java.util.Map;

@CrossOrigin
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Controller
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

	@GetMapping("/reportList")
	public ResponseEntity<?> getAllReports(@RequestParam int pageNo){
		final int PAGESIZE = 10;
		List<ReportDetailDto> reportTotalList = reportService.getAllReports(pageNo, PAGESIZE);
		return ResponseEntity.ok(reportTotalList);
	}

	@GetMapping("/reportCount")
	public ResponseEntity<?> getReportCount(){
		int reportCount = reportService.getReportCount();
		return ResponseEntity.ok(reportCount);
	}

	
	
	
	
}
