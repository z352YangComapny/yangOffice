package com.yangworld.app.domain.dm.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.yangworld.app.domain.dm.entity.Dm;

@Mapper
public interface DmRepository {

	@Insert("insert into dm values(seq_dm_id.nextval, #{receiverId}, #{senderId}, #{content}, #{dmRoomId}, default)")
	@SelectKey(
	    statement = "select seq_dm_id.currval from dual",
	    keyColumn = "id",
	    keyProperty = "id",
	    before = false,
	    resultType = int.class
	)
	int insertDm(Dm dm);

	@Select("select * from dm where sender_id = #{senderId}")
	List<Dm> findDmById(int senderId);

	@Select("select * from dm where receiver_id = #{receiverId}")
	List<Dm> findMyDm(int receiverId);
	
	@Select("select * from dm")
	List<Dm> findAllDm();

	@Select("select receiver_id, sender_id, content, reg_date from dm where (receiver_id=#{receiverId} and sender_id=#{senderId})")
	List<Dm> findDmDetails(int senderId, int receiverId);


}
