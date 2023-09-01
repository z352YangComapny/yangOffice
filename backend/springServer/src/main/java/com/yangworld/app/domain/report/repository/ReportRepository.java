package com.yangworld.app.domain.report.repository;

import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;
import org.apache.ibatis.annotations.*;

import com.yangworld.app.domain.report.entity.Report;
import com.yangworld.app.domain.report.entity.ReportDm;

import java.util.List;

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

	@Select("select * from photo_feed where id = #{feedId}")
	PhotoFeed findByFeedId(int feedId);

	@Insert("insert into report_photo_feed (report_id, photo_feed_id)values(seq_report_id.currval, #{feedId}) ")
	int insertReportFeed(int feedId);

	@Select("select * from comments where id = #{commentsId}")
	PhotoFeed findByCommentsId(int commentsId);

	@Insert("insert into report_comments_feed (report_id, comments_id) values(seq_report_id.currval, #{commentsId}) ")
	int insertReportFeedComments(int commentsId);
}
