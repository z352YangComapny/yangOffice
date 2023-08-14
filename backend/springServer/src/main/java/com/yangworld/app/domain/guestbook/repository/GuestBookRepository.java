package com.yangworld.app.domain.guestbook.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.yangworld.app.domain.guestbook.entity.GuestBook;

@Mapper
public interface GuestBookRepository {

	@Insert("insert into guestbook (id,writer_id,member_id,content,reg_date) values (seq_guestbook_id.nextval,#{writerId},#{memberId},#{content},default)")
	int insertGuestBook(GuestBook guestBook);

	@Delete("delete from guestbook where id = #{id} and writer_id = #{writerId}")
	int deleteGuestBook(GuestBook guestBook);

}
