package com.ssoystory.feedservice.domain.report.service;

import com.ssoystory.feedservice.domain.report.entity.FeedCommentsReport;
import com.ssoystory.feedservice.domain.report.entity.FeedCommentsReportId;
import com.ssoystory.feedservice.domain.report.entity.FeedReport;
import com.ssoystory.feedservice.domain.report.entity.FeedReportId;
import com.ssoystory.feedservice.domain.report.repository.FeedCommentsReportRepository;
import com.ssoystory.feedservice.domain.report.repository.FeedReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class FeedReportServiceImpl implements FeedReportService {
    @Autowired
    private FeedReportRepository feedReportRepository;
    @Autowired
    private FeedCommentsReportRepository feedCommentsReportRepository;


    @Override
    public void reportFeed(long id, long feedId) throws DataIntegrityViolationException {
        FeedReportId reportId = new FeedReportId();
        reportId.setReporterId(id);
        reportId.setReportedFeedId(feedId);
        FeedReport feedReport = new FeedReport();
        feedReport.setId(reportId);
        try {
            feedReportRepository.save(feedReport);
        } catch (DataIntegrityViolationException e){
            throw e;
        }
    }

    @Override
    public void reportFeedComments(long id, long feedCommentsId) throws DataIntegrityViolationException {
        FeedCommentsReportId feedCommentsReportId = new FeedCommentsReportId();
        feedCommentsReportId.setReporterId(id);
        feedCommentsReportId.setReportedFeedCommentsId(feedCommentsId);
        FeedCommentsReport feedCommentsReport = new FeedCommentsReport();
        feedCommentsReport.setId(feedCommentsReportId);
        try {
            feedCommentsReportRepository.save(feedCommentsReport);
        } catch (DataIntegrityViolationException e){
            throw e;
        }

    }
}
