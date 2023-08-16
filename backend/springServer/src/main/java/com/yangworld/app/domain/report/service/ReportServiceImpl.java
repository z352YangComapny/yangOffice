package com.yangworld.app.domain.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangworld.app.domain.report.entity.Report;
import com.yangworld.app.domain.report.entity.ReportDm;
import com.yangworld.app.domain.report.repository.ReportRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

	@Autowired	
	private ReportRepository reportRepository;
	
	@Override
	public int insertReport(Report report) {
		int result = reportRepository.insertReport(report);
		return result;
	}
	
	@Override
	public int insertReportDm(ReportDm reportDm) {
		int result = reportRepository.insertReportDm(reportDm);
		return result;
	}
}
