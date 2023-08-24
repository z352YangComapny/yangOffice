package com.yangworld.app.domain.report.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.data.repository.query.Param;

import com.yangworld.app.domain.report.dto.ReportStoryDto2;
import com.yangworld.app.domain.report.entity.Report;
import com.yangworld.app.domain.report.entity.ReportCommentsFeed;
import com.yangworld.app.domain.report.entity.ReportDm;
import com.yangworld.app.domain.report.entity.ReportGuestBook;
import com.yangworld.app.domain.report.entity.ReportPhotoFeed;
import com.yangworld.app.domain.report.entity.ReportProfile;


@Mapper
public interface ReportRepository {

	@Insert("insert into report(id, reporter_id, reported_id, content, reg_date) values(seq_report_id.nextval, #{reporterId}, #{reportedId}, #{content}, default)")
	@SelectKey(
	    statement = "select seq_report_id.currval from dual",
	    keyColumn = "id",
	    keyProperty = "id",
	    before = false,
	    resultType = int.class
	)
	int insertReport(Report report);

	// Report_DM 테이블에 신고와 DM 관계 추가
	@Insert("insert into report_dm(report_id, dm_id) values(#{reportId}, #{dmId})")
	int insertReportDm(ReportDm reportDm);

	@Insert("insert into report_guestbook(report_id, guestbook_id) values(#{reportId}, #{guestBookId})")
	int insertReportGuestBook(ReportGuestBook reportGuestbook);

	@Insert("insert into report_profile(report_id, profile_id) values(#{reportId}, #{profileId})")
	int insertReportProfile(ReportProfile reportProfile);

	@Insert("insert into report_photo_feed(report_id, photo_feed_id) values(#{reportId}, #{photoFeedId})")
	int insertReportFeed(ReportPhotoFeed reportFeed);
	
	@Insert("insert into report_comments_feed(report_id, comments_id) values(#{reportId}, #{commentsId})")
	int insertReportComments(ReportCommentsFeed reportCommentsFeed);
	
	@Insert("insert into report_story(report_id, story_id) values(#{reportId}, #{storyId})")
	void insertReportStory(ReportStoryDto2 reportStory);

	@Select("select max(id) from report")
	int findLastReportId();



}
