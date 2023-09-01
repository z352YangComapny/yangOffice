package com.yangworld.app.domain.report.controller;

import com.yangworld.app.domain.dm.entity.Dm;
import com.yangworld.app.domain.dm.service.DmService;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;
import com.yangworld.app.domain.photoFeed.service.PhotoFeedService;
import com.yangworld.app.domain.report.dto.ReportDetailDto;
import com.yangworld.app.domain.report.dto.ReportStoryDto;
import com.yangworld.app.domain.story.service.StoryService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@Autowired
	private StoryService storyService;

	@Autowired
	private PhotoFeedService photoFeedService;

	// insert 하실때 .insertReport 사용하시면 insert 됩니다. !!!!!!!!!!!!!!!!!!!!!!!!!!!!
	/*@GetMapping("/report/createDmReport")
	public void createReport() {
	}

	@GetMapping("/report/guestbookReport")
	public void guestbookReport() {

	}*/
	
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

	/*
	* GuestBook Report 생성
	* */
	@PostMapping("/reportGuestBook")
	public ResponseEntity<?> insertReportGuestBook(
			@AuthenticationPrincipal PrincipalDetails principal,
			@RequestBody ReportCreateDto _reportDto,
			@RequestParam int guestId
	) {
		int reporterId = principal.getId();
		Report report = _reportDto.toReport();
		log.info("report@Guest={}", report);

		reportService.insertReportGuestBook(report, guestId);

		return ResponseEntity.ok().build();
	}

	/**
	 * Story Report 기능
	 *
	 */
	@PostMapping("/reportStory")
	private ResponseEntity<?> insertReportStory(@AuthenticationPrincipal PrincipalDetails principal,
													 @RequestBody ReportCreateDto _reportDto, @RequestParam int storyId) {

		int reporterId = principal.getId();
		Report report = _reportDto.toReport();
		log.info("report@Guest={}", report);

		reportService.insertReportStory(report, storyId);

		return ResponseEntity.ok().build();

	}


	@PostMapping("/insertReportFeed")
	private ResponseEntity<?> insertReportFeed(
			@AuthenticationPrincipal PrincipalDetails principal,
			@RequestParam int feedId
	) {
		PhotoFeed photoFeed = photoFeedService.findPhotoFeedById(feedId);

		int reporterId = principal.getId();
		Report report = Report.builder()
						.reporterId(reporterId)
								.reportedId(photoFeed.getWriterId())
										.content(photoFeed.getContent())
												.build();
		log.info("report@Guest={}", report);

		reportService.insertReportFeed(report, feedId);

		return ResponseEntity.ok().build();
	}

	/**
	 * 포토피드 댓글신고
	 * */
	@PostMapping("/insertReportComment")
	private ResponseEntity<?> insertReportComments(@AuthenticationPrincipal PrincipalDetails principal
			, @RequestBody ReportCreateDto _reportDto, @RequestParam int commentsId) {

		int reporterId = principal.getId();
		Report report = _reportDto.toReport();
		log.info("report@Guest={}", report);

		reportService.insertReportComments(report, commentsId);

		return ResponseEntity.ok().build();
	}

	/*
	* reportList 조회
	* */
	@GetMapping("/reportList")
	public ResponseEntity<?> getAllReports(@RequestParam int pageNo){
		final int PAGESIZE = 10;
		List<ReportDetailDto> reportTotalList = reportService.getAllReports(pageNo, PAGESIZE);
		return ResponseEntity.ok(reportTotalList);
	}

	/*
	* reportCount 조회
	* */
	@GetMapping("/reportCount")
	public ResponseEntity<?> getReportCount(){
		int reportCount = reportService.getReportCount();
		return ResponseEntity.ok(reportCount);
	}

	
	
	
	
}
