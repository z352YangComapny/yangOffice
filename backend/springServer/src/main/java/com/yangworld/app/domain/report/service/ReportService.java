package com.yangworld.app.domain.report.service;

import com.yangworld.app.domain.report.dto.ReportDetailDto;
import com.yangworld.app.domain.report.entity.Report;
import com.yangworld.app.domain.report.entity.ReportDm;

import java.util.List;

public interface ReportService {

	int insertReportDm(Report report, int dmId);

    List<ReportDetailDto> getAllReports(int pageNo, int PAGESIZE);

    int getReportCount();

//	int insertReportDm(ReportDm reportDm);

}
