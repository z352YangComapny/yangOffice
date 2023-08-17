package com.yangworld.app.domain.report.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.data.repository.query.Param;

import com.yangworld.app.domain.report.entity.Report;
import com.yangworld.app.domain.report.entity.ReportDm;
import com.yangworld.app.domain.report.entity.ReportGuestBook;

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
	int insertReportGuestBook(ReportGuestBook reportGuestBook);
}
