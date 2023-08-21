package com.yangworld.app.domain.report.service;

import com.yangworld.app.domain.report.entity.Report;
import com.yangworld.app.domain.report.entity.ReportDm;

public interface ReportService {

	int insertReportDm(Report report, int dmId);

	int insertReportGuestBook(Report report, int reportGuestbook);

	int insertReport(Report report);


	int insertReportProfile(Report report, int profileId);


//	int insertReportDm(ReportDm reportDm);

}
