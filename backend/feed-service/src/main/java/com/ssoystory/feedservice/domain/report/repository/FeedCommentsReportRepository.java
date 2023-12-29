package com.ssoystory.feedservice.domain.report.repository;

import com.ssoystory.feedservice.domain.report.entity.FeedCommentsReport;
import com.ssoystory.feedservice.domain.report.entity.FeedCommentsReportId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedCommentsReportRepository extends JpaRepository<FeedCommentsReport, FeedCommentsReportId> {
}
