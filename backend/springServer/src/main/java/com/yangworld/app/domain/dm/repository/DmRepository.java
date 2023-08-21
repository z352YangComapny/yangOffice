package com.yangworld.app.domain.dm.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;

import com.yangworld.app.domain.dm.entity.Dm;
import com.yangworld.app.domain.dm.entity.DmRoom;

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

	@Select("select * from dm where dm_room_id = #{dmRoomId} order by reg_date asc")
	List<Dm> findDmDetails(int dmRoomId);
	
	@Select("SELECT dr.id AS dm_room_id, dr.participant1, dr.participant2, dm.id AS dm_id, dm.receiver_id, dm.sender_id, dm.content, dm.reg_date " +
	        "FROM dm_room dr " +
	        "JOIN dm ON dr.id = dm.dm_room_id " +
	        "WHERE dr.participant1 = #{userId} OR dr.participant2 = #{userId}")
	List<Dm> findMyDmList(int userId);

	
	@Insert("insert into dm_room values (seq_dm_room_id.nextval , #{participant1},#{participant2}, default)")
    void insertDmRoom(@Param("participant1") int participant1, @Param("participant2") int participant2);

	@Delete("delete from dm_room where id=${dmRoomId}")
	int deleteDmRoom(int dmRoomId);

    @Select("SELECT m.username FROM dm d JOIN member m ON d.receiver_id = m.id WHERE d.id = #{id}")
	String getUsernameById(int id);

    @Select("SELECT * FROM dm_room WHERE id=${dmRoomId}")
	List<DmRoom> findDmRoom(int dmRoomId) ;

    @Select("SELECT dm.sender_id, "
            + "(SELECT username FROM member WHERE id = dm.sender_id) AS sender_username, "
            + "dm.receiver_id, "
            + "(SELECT username FROM member WHERE id = dm.receiver_id) AS receiver_username "
            + "FROM dm "
            + "WHERE dm.id = #{dmRoomId}")
	List<Map<String, Object>> findMemberId(int dmRoomId);

	
}
