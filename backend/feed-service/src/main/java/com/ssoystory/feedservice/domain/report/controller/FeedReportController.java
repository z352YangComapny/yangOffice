package com.ssoystory.feedservice.domain.report.controller;

import com.ssoystory.feedservice.domain.report.dto.FeedCommentsReportDto;
import com.ssoystory.feedservice.domain.report.dto.FeedReportDto;
import com.ssoystory.feedservice.domain.report.service.FeedReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feed/report")
@Slf4j
public class FeedReportController {
    @Autowired
    private FeedReportService feedReportService;

    @PostMapping
    ResponseEntity<?> ReportFeed(@RequestHeader("x-authorization-id") long id, @RequestBody FeedReportDto feedId) {
        log.info("id={}, feedId={}", id,feedId);
        try {
            feedReportService.reportFeed(id, feedId.getFeedId());
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException exception){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/comments")
    ResponseEntity<?> ReportFeedComments(@RequestHeader("x-authorization-id") long id, @RequestBody FeedCommentsReportDto feedCommentsId) {
        log.info("id={}, feedCommentsId={}", id,feedCommentsId);
        try {
            feedReportService.reportFeedComments(id, feedCommentsId.getFeedCommnetsId());
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException exception){
            return ResponseEntity.badRequest().build();
        }
    }
}
