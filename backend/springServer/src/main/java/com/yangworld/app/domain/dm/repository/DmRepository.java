package com.yangworld.app.domain.dm.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.yangworld.app.domain.dm.dto.DmDto;

@Mapper
public interface DmRepository {

	@Insert("insert into dm values(seq_dm_id.nextval, #{receiverId}, #{senderId}, #{content}, default)")
	int insertDm(DmDto dmDto);

}
