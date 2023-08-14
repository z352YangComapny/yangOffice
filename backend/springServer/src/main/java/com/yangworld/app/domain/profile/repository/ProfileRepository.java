package com.yangworld.app.domain.profile.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

import com.yangworld.app.domain.profile.dto.ProfileDto;

@Mapper
public interface ProfileRepository {

	@Insert("INSERT INTO profile (id, member_id, state, introduction) " +
            "VALUES (seq_profile_id.nextval, #{memberId}, #{state}, #{introduction})")
    @SelectKey(
            statement = "SELECT seq_profile_id.currval FROM dual",
            keyColumn = "id",
            keyProperty = "id",
            before = false,
            resultType = int.class
    )
    int insertProfile(ProfileDto profile);
}
