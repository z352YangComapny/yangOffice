package com.yangworld.app.domain.chat.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.yangworld.app.domain.chat.entity.Chat;

@Mapper
public interface ChatRepository {

	@Insert("INSERT INTO chat VALUES(seq_chat_id.nextval, #{memberId}, #{chatContent}, default, 'N')")
	@SelectKey(
		    statement = "select seq_chat_id.currval from dual",
		    keyColumn = "id",
		    keyProperty = "id",
		    before = false,
		    resultType = int.class
		)
	int sendChat(Chat chat);

	@Select("SELECT * FROM CHAT")
	List<Chat> findChatList();

	
}
