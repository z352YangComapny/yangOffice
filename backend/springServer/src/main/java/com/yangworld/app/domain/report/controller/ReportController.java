package com.yangworld.app.domain.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.report.dto.ReportCreateDto;
import com.yangworld.app.domain.report.entity.Report;
import com.yangworld.app.domain.report.service.ReportService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	// insert 하실때 .insertReport 사용하시면 insert 됩니다. !!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	@GetMapping("/createDmReport")
	public void createReport() {
		
	}
	
	/**
	 * 윤아
	 * dm report 인서트 
	 */
	@PostMapping("/insertReportDm")
	private String insertReportDm(@AuthenticationPrincipal PrincipalDetails principal	, @ModelAttribute ReportCreateDto _reportDto, @RequestParam int dmId, @RequestParam int reportedId,
			RedirectAttributes redirectAttributes) {
		
		int reporterId = principal.getId();
		
		Report report = _reportDto.toReport();
		log.info("report = {}", report);
		report.setReporterId(reporterId);
		report.setReportedId(reportedId);
		
		// 1. report 테이블에 insert
		reportService.insertReportDm(report, dmId); // reportId = report 시퀀스값
		
		 redirectAttributes.addFlashAttribute("msg", "신고가 정상적으로 접수되었습니다.");
		
		return "redirect:/dm/dmList";
	}

	@PostMapping("/insertReportGuestBook.do")
	public ResponseEntity<?> insertReportGuestBook(
			@AuthenticationPrincipal PrincipalDetails principalDetails,
			ReportCreateDto _reportDto,
			@RequestParam int reportGuestbook,
			@RequestParam int reportedId,
			@RequestParam String reportContent
		){
		
		int reporterId = principalDetails.getId();
		
		Report report = _reportDto.toReport();
		report.setReporterId(reporterId);
		report.setReportedId(reportedId);
		report.setContent(reportContent);
		
		log.info("report = {}",report);
		int result = reportService.insertReport(report);
		log.info("result1={}",result);
		result += reportService.insertReportGuestBook(report,reportGuestbook);
		log.info("result2={}",result);
		
		return ResponseEntity.ok().build();
	}

	
	@PostMapping("/insertReportProfile")
	public ResponseEntity<?> insertReportProfile(
			@AuthenticationPrincipal PrincipalDetails principalDetails,
			@RequestBody ReportCreateDto _reportDto,
			@RequestParam int profileId
		){
		
		int reporterId = principalDetails.getId();
		
		Report report = _reportDto.toReport();
		log.info("report = {}",report);
		report.setReporterId(reporterId);
		
		
		reportService.insertReportProfile(report,profileId);
		
		return ResponseEntity.ok().build();
	}

	
	
	
	
}
