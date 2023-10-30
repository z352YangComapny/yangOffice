package com.ssoystory.feedservice.domain.report.service;

import com.ssoystory.feedservice.domain.report.entity.FeedReport;
import com.ssoystory.feedservice.domain.report.entity.FeedReportId;
import com.ssoystory.feedservice.domain.report.repository.FeedReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedReportServiceImpl implements FeedReportService {
    @Autowired
    private FeedReportRepository feedReportRepository;
    @Override
    public void save(long authorId, long feedId) {
        FeedReportId reportId = new FeedReportId();
        reportId.setReporterId(authorId);
        reportId.setReportedFeedId(feedId);
        FeedReport feedReport = new FeedReport();
        feedReport.setId(reportId);
        feedReportRepository.save(feedReport);
    }
}
