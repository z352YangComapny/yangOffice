package com.ssoystory.feedservice.domain.report.repository;

import com.ssoystory.feedservice.domain.report.entity.FeedReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedReportRepository extends JpaRepository<FeedReport,Long> {
}
