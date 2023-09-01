package com.yangworld.app.domain.guestbook.repository;

import com.yangworld.app.domain.guestbook.dto.GuestbookDailyDto;
import org.apache.ibatis.annotations.*;

import com.yangworld.app.domain.guestbook.entity.GuestBook;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface GuestBookRepository {


	@Select("select * from guestbook where id = #{guestBookId}")
	GuestBook findGuestBookById(int guestBookId);

	@Insert("insert into guestbook (id,writer_id,member_id,content,reg_date) values (seq_guestbook_id.nextval,#{writerId},#{memberId},#{content},default)")
	int insertGuestBook(GuestBook guestBook);

	@Delete("delete from guestbook where id = #{id} and writer_id = #{writerId}")
	int deleteGuestBook(GuestBook guestBook);

	@Update("update guestbook set content = #{content} where id = #{id}")
	int updateGuestBook(GuestBook guestBook);
	@Select("select count(*) from guestbook")
	int guestbookTotalNo();
	@Select("SELECT\n" +
			"    TRUNC(dates.guestbook_date) AS guestbook_date,\n" +
			"    NVL(COUNT(GUESTBOOK.reg_date), 0) AS guestbook_count\n" +
			"FROM\n" +
			"    (SELECT TRUNC(SYSDATE) - LEVEL + 1 AS guestbook_date\n" +
			"     FROM dual\n" +
			"     CONNECT BY LEVEL <= 10) dates\n" +
			"        LEFT JOIN\n" +
			"    GUESTBOOK ON TRUNC(GUESTBOOK.reg_date) = dates.guestbook_date\n" +
			"GROUP BY\n" +
			"    TRUNC(dates.guestbook_date)\n" +
			"ORDER BY\n" +
			"    TRUNC(dates.guestbook_date)")
	List<GuestbookDailyDto> findGuestBookDaily();

	@Select("select * from guestbook order by reg_date desc")
	List<GuestBook> guestBookList(RowBounds rowBounds);
}
