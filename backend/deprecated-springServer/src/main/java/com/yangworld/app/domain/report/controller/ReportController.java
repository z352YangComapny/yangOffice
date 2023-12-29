package com.yangworld.app.domain.report.controller;

import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.comments.service.CommentsService;
import com.yangworld.app.domain.dm.entity.Dm;
import com.yangworld.app.domain.dm.service.DmService;
import com.yangworld.app.domain.guestbook.entity.GuestBook;
import com.yangworld.app.domain.guestbook.service.GuestBookService;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;
import com.yangworld.app.domain.photoFeed.service.PhotoFeedService;
import com.yangworld.app.domain.report.dto.ReportDetailDto;
import com.yangworld.app.domain.report.dto.ReportStoryDto;
import com.yangworld.app.domain.story.entity.Story;
import com.yangworld.app.domain.story.service.StoryService;
import lombok.RequiredArgsConstructor;
import com.yangworld.app.domain.report.dto.ReportFeedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

	@Autowired
	private CommentsService commentsService;

	@Autowired
	private GuestBookService guestBookService;

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

	/*
	* GuestBook Report 생성
	* */
	@PostMapping("/reportGuestBook")
	public ResponseEntity<?> insertReportGuestBook(
			@AuthenticationPrincipal PrincipalDetails principal,
			@RequestParam int guestbookId
	) {
		GuestBook guestBook = guestBookService.findGuestBookById(guestbookId);
		int reporterId = principal.getId();
		Report report = Report.builder()
				.reporterId(reporterId)
				.reportedId(guestBook.getWriterId())
				.content(guestBook.getContent())
				.build();
		log.info("report@Guest={}", report);

		reportService.insertReportGuestBook(report, guestbookId);

		return ResponseEntity.ok().build();
	}

	/**
	 * Story Report 기능
	 *
	 */
	@PostMapping("/reportStory")
	private ResponseEntity<?> insertReportStory(@AuthenticationPrincipal PrincipalDetails principal,
													 @RequestParam int storyId) {

		Story story = storyService.findStoryOriginById(storyId);
		int reporterId = principal.getId();
		Report report = Report.builder()
				.reporterId(reporterId)
				.reportedId(story.getWriterId())
				.content(story.getContent())
				.build();
		log.info("report@Guest={}", report);

		reportService.insertReportStory(report, storyId);

		return ResponseEntity.ok().build();

	}


	/**
	 * 포토피드 report
	 * */
	@PostMapping("/insertReportFeed")
	private ResponseEntity<?> insertReportPhotoFeed(
			@AuthenticationPrincipal PrincipalDetails principal,
			@RequestParam int feedId
	) {
		PhotoFeed photoFeed = photoFeedService.findPhotoFeedById(feedId);
	 	log.info("photoFeed@Repoart={}", photoFeed);
		 log.info("principal@report={}", principal);
		int reporterId = principal.getId();
		log.info("reporterId={}", reporterId);
		Report report = Report.builder()
						.reporterId(reporterId)
								.reportedId(photoFeed.getWriterId())
										.content(photoFeed.getContent())
												.build();
		log.info("report@Guest={}", report);

		reportService.insertReportPhotoFeed(report, feedId);

		return ResponseEntity.ok().build();
	}

	/**
	 * 포토피드 댓글신고
	 * */
	@PostMapping("/insertReportComment")
	private ResponseEntity<?> insertReportComments(@AuthenticationPrincipal PrincipalDetails principal
			, @RequestParam int commentsId) {
		Comments comments = commentsService.findCommentById(commentsId);

		int reporterId = principal.getId();
		Report report = Report.builder()
				.reporterId(reporterId)
				.reportedId(comments.getWriterId())
				.content(comments.getContent())
				.build();
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
