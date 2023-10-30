package com.ssoystory.feedservice.domain.report.controller;

import com.ssoystory.feedservice.domain.report.service.FeedReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feed/report")
public class FeedReportController {
    @Autowired
    private FeedReportService feedReportService;

    @PostMapping
    ResponseEntity<?> ReportFeed(@RequestPart long authorId, @RequestPart long feedId) {
        feedReportService.save(authorId, feedId);
        return ResponseEntity.ok().build();
    }

}
