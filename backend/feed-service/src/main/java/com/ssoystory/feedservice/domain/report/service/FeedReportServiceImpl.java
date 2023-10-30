package com.ssoystory.feedservice.domain.report.service;

import com.ssoystory.feedservice.domain.report.entity.FeedReport;
import com.ssoystory.feedservice.domain.report.repository.FeedReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedReportServiceImpl implements FeedReportService {
    @Autowired
    private FeedReportRepository feedReportRepository;
    @Override
    public void save(long authorId, long feedId) {
        feedReportRepository.save(FeedReport.builder().reporterId(authorId).reportedFeedId(feedId).build());
    }
}
