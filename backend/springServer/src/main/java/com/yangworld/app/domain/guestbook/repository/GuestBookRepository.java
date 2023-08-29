package com.yangworld.app.domain.guestbook.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.yangworld.app.domain.guestbook.dto.GuestBookCreateDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookDeleteDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookUpdateDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookWithNicknameDto;
import com.yangworld.app.domain.guestbook.entity.GuestBook;

@Mapper
public interface GuestBookRepository {

	@Insert("insert into guestbook (id,writer_id,member_id,content,reg_date) values (seq_guestbook_id.nextval,#{writerId},#{memberId},#{content},default)")
	int insertGuestBook(GuestBookCreateDto guestBook);

	@Delete("delete from guestbook where id = #{id}")
	int deleteGuestBook(int deleteGuestbook);

	@Update("update guestbook set content = #{content} where id = #{id}")
	int updateGuestBook(GuestBookUpdateDto _guestBook);

	@Select("select g.*, m.nickName from guestbook g left join member m on g.writer_id = m.id where g.member_id = #{member_id} order by g.reg_date desc")
	List<GuestBookWithNicknameDto> findAll(RowBounds rowBounds, int memberId);

	@Select("select count(*) from guestbook where member_id = #{member_id}")
	int countAllGuestbook(int memberId);

	@Select("select g.*, m.nickName from guestbook g left join member m on g.writer_id = m.id where g.member_id=#{id}")
	List<GuestBookWithNicknameDto> findReportedId(int id);

}
