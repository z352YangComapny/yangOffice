package com.yangworld.app.domain.profile.repository;


import com.yangworld.app.domain.profile.entity.Profile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProfileRepository {
	
    @Select("select * from profile where member_id = #{id}")
    Profile findProfileByMemberIdForAdmin(int id);
}
