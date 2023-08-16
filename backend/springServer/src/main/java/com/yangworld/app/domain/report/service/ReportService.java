package com.yangworld.app.domain.report.service;

import com.yangworld.app.domain.report.entity.Report;
import com.yangworld.app.domain.report.entity.ReportDm;

public interface ReportService {

	int insertReport(Report report);

	int insertReportDm(ReportDm reportDm);

}
