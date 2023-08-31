package com.yangworld.app.domain.report.repository;

import com.yangworld.app.domain.report.dto.ReportDetailDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.data.repository.query.Param;

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


	@Select("select r.id as id, m.username as writer, r.content as content, r.REG_DATE as regdate from report r join REPORT_PHOTO_FEED rpf on r.ID = rpf.REPORT_ID join PHOTO_FEED pf on rpf.PHOTO_FEED_ID=pf.id join MEMBER m on r.REPORTED_ID = m.id")
	List<ReportDetailDto> getPotofeedReport();

	@Select("select\n" +
			"    r.id as id,\n" +
			"    m.username as writer,\n" +
			"    r.content as content,\n" +
			"    r.REG_DATE as regdate\n" +
			"from report r\n" +
			"    join REPORT_STORY rs on r.id = rs.REPORT_ID\n" +
			"    join STORY s on rs.STORY_ID = s.id\n" +
			"    join MEMBER m on r.REPORTED_ID = m.id")
	List<ReportDetailDto> getReportStory();

	@Select("select\n" +
			"    r.id as id,\n" +
			"    m.username as writer,\n" +
			"    r.content as content,\n" +
			"    r.REG_DATE as regdate\n" +
			"from report r\n" +
			"     join REPORT_DM rd on r.id = rd.REPORT_ID\n" +
			"     join DM d on rd.DM_ID = d.id\n" +
			"     join MEMBER M on r.REPORTED_ID = M.ID")
	List<ReportDetailDto> getReportDm();

	@Select("select  r.id as id,\n" +
			"        m.username as writer,\n" +
			"        r.content as content,\n" +
			"        r.REG_DATE as regdate\n" +
			"from report r\n" +
			"    join REPORT_COMMENTS_FEED rcf on r.id = rcf.REPORT_ID\n" +
			"    join COMMENTS_FEED cf on rcf.COMMENTS_ID = cf.COMMENTS_ID\n" +
			"    join comments c on cf.COMMENTS_ID = c.id\n" +
			"    join MEMBER m on r.REPORTED_ID = m.id")
	List<ReportDetailDto> getReportCmt();

	@Select("select\n" +
			"    r.id as id,\n" +
			"    m.username as writer,\n" +
			"    r.content as content,\n" +
			"    r.REG_DATE as regdate\n" +
			"    from report r\n" +
			"    join REPORT_GUESTBOOK rg on r.id = rg.REPORT_ID\n" +
			"    join GUESTBOOK g on rg.GUESTBOOK_ID = g.id\n" +
			"    join MEMBER m on r.REPORTED_ID = m.id")
	List<ReportDetailDto> getReportGuestBook();

	@Select("select count(*) from report")
	int getReportCount();
}
