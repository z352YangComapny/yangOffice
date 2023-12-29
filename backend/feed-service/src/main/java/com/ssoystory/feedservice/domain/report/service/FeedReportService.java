package com.ssoystory.feedservice.domain.report.service;

public interface FeedReportService {

    void reportFeed(long id, long feedId);

    void reportFeedComments(long id, long feedCommentsId);
}
