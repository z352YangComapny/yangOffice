package com.yangworld.app.domain.report.service;

import com.yangworld.app.domain.report.dto.ReportStoryDto2;
import com.yangworld.app.domain.report.entity.Report;
import com.yangworld.app.domain.report.entity.ReportDm;

public interface ReportService {

	int insertReportDm(Report report, int dmId);

	int insertReportGuestBook(Report report, int guestbookId);


	int insertReportProfile(Report report, int profileId);

	int insertReportFeed(Report report, int feedId);

	int insertReportComments(Report report, int commentsId);

	void insertReportStory(Report report);

	void insertReportStory2(ReportStoryDto2 reportStory);

	int findLastReportId();


//	int insertReportDm(ReportDm reportDm);

}
