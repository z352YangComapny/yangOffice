package com.yangworld.app.domain.report.service;

import com.yangworld.app.domain.report.dto.ReportDetailDto;
import com.yangworld.app.domain.report.dto.ReportStoryDto2;
import com.yangworld.app.domain.report.entity.Report;
import com.yangworld.app.domain.report.entity.ReportDm;

import java.util.List;

public interface ReportService {

	int insertReportDm(Report report, int dmId);

    List<ReportDetailDto> getAllReports(int pageNo, int PAGESIZE);

    int getReportCount();

//	int insertReportDm(ReportDm reportDm);

    int insertReportGuestBook(Report report, int guestbookId);


    int insertReportProfile(Report report, int profileId);

    int insertReportFeed(Report report, int feedId);

    int insertReportComments(Report report, int commentsId);

    int insertReportStory(Report report, int storyId);




}
