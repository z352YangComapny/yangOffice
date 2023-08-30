package com.yangworld.app.domain.guestbook.repository;

import org.apache.ibatis.annotations.*;

import com.yangworld.app.domain.guestbook.dto.GuestBookWithNicknameDto;
import com.yangworld.app.domain.guestbook.entity.GuestBook;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface GuestBookRepository {

	@Insert("insert into guestbook (id,writer_id,member_id,content,reg_date) values (seq_guestbook_id.nextval,#{writerId},#{memberId},#{content},default)")
	int insertGuestBook(GuestBook guestBook);

	@Delete("delete from guestbook where id = #{id} and writer_id = #{writerId}")
	int deleteGuestBook(GuestBook guestBook);

	@Update("update guestbook set content = #{content} where id = #{id}")
	int updateGuestBook(GuestBook guestBook);
	@Select("select count(*) from guestbook")
	int guestbookTotalNo();
	@Select("select * from guestbook order by reg_date desc")
	List<GuestBook> guestBookList(RowBounds rowBounds);

	@Select("select g.*, m.nickName from guestbook g left join member m on g.writer_id = m.id where g.member_id = #{memberId} order by g.reg_date desc")
	List<GuestBookWithNicknameDto> findAll(RowBounds rowBounds, int memberId);

	@Select("select count(*) from guestbook where member_id = #{id}")
	int countAllGuestBook(int id);
}
