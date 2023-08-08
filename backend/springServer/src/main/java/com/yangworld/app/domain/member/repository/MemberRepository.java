package com.yangworld.app.domain.member.repository;

import com.yangworld.app.domain.member.dto.SignUpDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.yangworld.app.domain.member.entity.Member;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberRepository {
	Member findMemberByUsername(String username);
    @Insert("insert into member values(seq_member_id.nextval, #{username}, #{password}, #{name}, #{gender}, #{email, jdbcType=VARCHAR}, #{phone}, default, 'yang', default)")
    int insertMember(SignUpDto signUpDto);
}
