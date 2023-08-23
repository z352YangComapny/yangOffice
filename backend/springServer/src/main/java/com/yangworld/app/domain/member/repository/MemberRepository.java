package com.yangworld.app.domain.member.repository;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.member.dto.FollowDto;
import com.yangworld.app.domain.member.dto.SignUpDto;
import com.yangworld.app.domain.member.dto.UpdateDto;
import org.apache.ibatis.annotations.*;
import com.yangworld.app.domain.member.entity.Member;

import java.util.List;

@Mapper
public interface MemberRepository {
    @Insert("insert into member values(seq_member_id.nextval, #{username},  #{name}, #{password}, #{nickname}, #{birthday, jdbcType = DATE}, #{gender}, #{phone}, #{email, jdbcType=VARCHAR}, 'YANG', default)")
    @SelectKey(
            before = false,
            keyProperty = "id",
            resultType = int.class,
            statement = "select seq_member_id.currval from dual")
    int insertMember(SignUpDto signUpDto);

    int insertAuthorities(@Param("id") int id, @Param("authorityList") List<String> authorityList);

    PrincipalDetails loadUserByUsername(String username);

    @Update("update member set password=#{password} where username = #{username}")
    void updatePassword(@Param("username") String username, @Param("password") String password);


    @Update("update member set nickname = #{updateDto.nickname}, phone = #{updateDto.phone}, email=#{updateDto.email}, birthday = #{updateDto.birthday} where username = #{username}")
    int updateMember(@Param("updateDto") UpdateDto updateDto, @Param("username") String username);

    @Delete("delete from member where username = #{username}")
    int deleteMember(String username);

    @Insert("insert into follow values (#{follower}, #{followee}, default)")
    int insertFollowee(FollowDto followDto);

    @Delete("delete from follow where follower = #{follower} and followee = #{followee}")
    int deleteFollowee(FollowDto unfollow);

    @Select("select * from member where email = #{email}")
    Member findMemberByEmail(String email);

    
    @Select("select * from member where id = #{writer}")
	Member findById(int writer);

    @Select("select * from member where nickname = #{nickname}")
    Member findByNickname(String nickname);

    @Select("select *from member where phone = #{phone}")
    Member findByPhone(String phone);

    @Update("update member set password = #{newPassword} where username = #{username}")
    int resetPassword(@Param("newPassword")String newPassword, @Param("username")String username);

    @Select("select * from member")
    List<String> findAllMember();
}

