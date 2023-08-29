package com.yangworld.app.domain.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.report.dto.ReportCreateDto;
import com.yangworld.app.domain.report.dto.ReportStoryDto;
import com.yangworld.app.domain.report.dto.ReportStoryDto2;
import com.yangworld.app.domain.report.entity.Report;
import com.yangworld.app.domain.report.service.ReportService;
import com.yangworld.app.domain.story.service.StoryService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private StoryService storyService;

    // insert 하실때 .insertReport 사용하시면 insert 됩니다. !!!!!!!!!!!!!!!!!!!!!!!!!!!!

    @GetMapping("/createDmReport")
    public void createReport() {

    }

    @GetMapping("/guestbookReport")
    public void guestbookReport() {

    }


    /**
     * 윤아
     * dm report 인서트
     */
    @PostMapping("/insertReportDm")
    private String insertReportDm(@AuthenticationPrincipal PrincipalDetails principal, @ModelAttribute ReportCreateDto _reportDto, @RequestParam int dmId, @RequestParam int reportedId,
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
    public String insertReportGuestBook(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @ModelAttribute ReportCreateDto _reportDto,
            @RequestParam int guestbookId,
            @RequestParam int reportedId,
            RedirectAttributes redirectAttributes
    ) {

        int reporterId = principalDetails.getId();

        Report report = _reportDto.toReport();
        report.setReporterId(reporterId);
        report.setReportedId(reportedId);


        log.info("report@cont = {}", report);

        reportService.insertReportGuestBook(report, guestbookId);
        redirectAttributes.addFlashAttribute("msg", "신고가 정상적으로 접수되었습니다.");

        return "redirect:/guestbook/guestbook.do";
    }


    @PostMapping("/insertReportProfile")
    public ResponseEntity<?> insertReportProfile(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody ReportCreateDto _reportDto,
            @RequestParam int profileId
    ) {

        int reporterId = principalDetails.getId();

        Report report = _reportDto.toReport();
        log.info("report = {}", report);
        report.setReporterId(reporterId);


        reportService.insertReportProfile(report, profileId);

        return ResponseEntity.ok().build();
    }


    @PostMapping("/member/userPage/{id}/insertReportFeed")
    private String insertReportFeed(
            @AuthenticationPrincipal PrincipalDetails principal,
            @RequestParam String content,
            @RequestParam int feedId,
            @RequestParam int reportedId,
            @RequestParam int reporterId,
            @PathVariable("id") int id,
            @ModelAttribute ReportCreateDto _reportDto,
            RedirectAttributes redirectAttributes
    ) {
        Report report = _reportDto.toReport();
        report.setContent(content);
        report.setReporterId(reporterId);
        report.setReportedId(reportedId);
        log.info("report ={}", report);
        // 1. report 테이블에 insert
        int result = reportService.insertReportFeed(report, feedId); // reportId = report 시퀀스값

        redirectAttributes.addFlashAttribute("msg", "신고가 정상적으로 접수되었습니다.");

        return "redirect:/member/userPage/" + id + "/feed/feedDetail?photoFeedId=" + feedId;
    }

    @PostMapping("/member/userPage/{id}/insertReportComment")
    private String insertReportComments(@AuthenticationPrincipal PrincipalDetails principal
            , @ModelAttribute ReportCreateDto _reportDto,
                                        @RequestParam int commentsId,
                                        @RequestParam int reportedId,
                                        @RequestParam int feedId,
                                        @RequestParam int reporterId,
                                        @RequestParam String content,
                                        @PathVariable("id") int id,
                                        RedirectAttributes redirectAttributes) {


        Report report = _reportDto.toReport();
        log.info("report = {}", report);
        report.setContent(content);
        report.setReporterId(reporterId);
        report.setReportedId(reportedId);

        // 1. report 테이블에 insert
        int result = reportService.insertReportComments(report, commentsId); // reportId = report 시퀀스값

        redirectAttributes.addFlashAttribute("msg", "신고가 정상적으로 접수되었습니다.");

        return "redirect:/member/userPage/" + id + "/feed/feedDetail?photoFeedId=" + feedId;
    }

    @PostMapping("/createStoryReport")
    private String insertReportStory(ReportStoryDto story, RedirectAttributes redirectAttributes) {
        log.info("story = {}", story);
        int reportedId = storyService.findIdByUsername(story.getReportedName());
        Report report = Report.builder()
                .reporterId(story.getReporterId())
                .reportedId(reportedId)
                .content(story.getContent())
                .build();
        log.info("report = {}", report);
        reportService.insertReportStory(report);
        int reportId = reportService.findLastReportId();
        ReportStoryDto2 reportStory = ReportStoryDto2.builder()
                .reportId(reportId)
                .storyId(story.getStoryId())
                .build();
        log.info("reportStory = {}", reportStory);
        reportService.insertReportStory2(reportStory);

        redirectAttributes.addFlashAttribute("msg", "신고가 정상적으로 접수되었습니다.");
        return "redirect:/member/userPage/" + story.getReporterId();
    }


}
