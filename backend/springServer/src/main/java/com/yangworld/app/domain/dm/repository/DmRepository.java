package com.yangworld.app.domain.dm.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

import com.yangworld.app.domain.dm.dto.DmDto;
import com.yangworld.app.domain.dm.entity.Dm;

@Mapper
public interface DmRepository {

	@Insert("insert into dm values(seq_dm_id.nextval, #{receiverId}, #{senderId}, #{content}, default)")
	@SelectKey(
	    statement = "select seq_dm_id.currval from dual",
	    keyColumn = "id",
	    keyProperty = "id",
	    before = false,
	    resultType = int.class
	)
	int insertDm(Dm dm);


}
