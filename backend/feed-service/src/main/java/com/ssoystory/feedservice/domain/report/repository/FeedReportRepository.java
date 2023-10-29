package com.ssoystory.feedservice.domain.report.repository;

import com.ssoystory.feedservice.domain.report.entity.FeedReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedReportRepository extends JpaRepository<FeedReport,Long> {
}
