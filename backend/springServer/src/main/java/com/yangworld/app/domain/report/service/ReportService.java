package com.yangworld.app.domain.report.service;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.report.dto.ReportCreateDto;
import com.yangworld.app.domain.report.dto.ReportFeedRequest;
import com.yangworld.app.domain.report.entity.Report;
import com.yangworld.app.domain.report.entity.ReportDm;

public interface ReportService {

	int insertReportDm(Report report, int dmId);

    int insertReportFeed(PrincipalDetails principalDetails, ReportFeedRequest feedRequest);

//	int insertReportDm(ReportDm reportDm);

}
