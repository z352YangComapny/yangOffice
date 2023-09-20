package com.yangworld.app.domain.dm.repository;

import java.util.List;

import com.yangworld.app.domain.dm.entity.DmRoom;
import org.apache.ibatis.annotations.*;

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

	@Select("select * from dm where dm_room_id = #{dmRoomId} order by reg_date desc")
	List<Dm> findDmDetails(int dmRoomId);
	
	@Select("SELECT dr.id AS dm_room_id, dr.participant1, dr.participant2, dm.id AS dm_id, dm.receiver_id, dm.sender_id, dm.content, dm.reg_date " +
	        "FROM dm_room dr " +
	        "JOIN dm ON dr.id = dm.dm_room_id " +
	        "WHERE dr.participant1 = #{userId} OR dr.participant2 = #{userId}")
	List<Dm> findMyDmList(int userId);

	
	@Insert("insert into dm_room values (seq_dm_room_id.nextval , #{participant1},#{participant2}, default)")
    void insertDmRoom(@Param("participant1") int participant1, @Param("participant2") int participant2);

	@Delete("delete from dm_room where id= #{dmRoomId}")
	int deleteDmRoom(int dmRoomId);


	@Select("select * from dm_room where PARTICIPANT2=#{id} or PARTICIPANT1=#{id}")
    List<DmRoom> findDmRoomByParticipantId(int id);

}
