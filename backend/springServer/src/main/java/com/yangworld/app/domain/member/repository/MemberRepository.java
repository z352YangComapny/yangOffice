package com.yangworld.app.domain.member.repository;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.member.dto.*;
import org.apache.ibatis.annotations.*;
import com.yangworld.app.domain.member.entity.Member;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface MemberRepository {
    @Insert("insert into member values(seq_member_id.nextval, #{username},  #{name}, #{password}, #{nickname}, #{birthday}, #{gender}, #{phone}, #{email, jdbcType=VARCHAR}, 'YANG', default)")
    @SelectKey(
            before = false,
            keyProperty = "id",
            resultType = int.class,
            statement = "select seq_member_id.currval from dual")
    int insertMember(SignUpDto signUpDto);
    
    int insertAuthorities(@Param("id")int id,@Param("authorityList") List<String> authorityList);

    PrincipalDetails loadUserByUsername(String username);

    @Update("update member set password=#{password} where username = #{username}")
    void updatePassword(@Param("username") String username, @Param("password") String password);


    @Update("update member set password = #{updateDto.password}, nickname = #{updateDto.nickname}, phone = #{updateDto.phone}, email=#{updateDto.email}, birthday = #{updateDto.birthday} where username = #{username}")
    int updateMember(@Param("updateDto") UpdateDto updateDto, @Param("username") String username);

    @Delete("delete from member where username = #{username}")
    int deleteMember(String username);

    @Insert("insert into follow values (#{follower}, #{followee}, default)")
    int insertFollowee(FollowDto followDto);

    @Delete("delete from follow where follower = #{follower} and followee = #{followee}")
    int deleteFollowee(FollowDto unfollow);

    @Select("select username from member where email = #{email}")
    String findMemberByEmail(FindIdDto findIdDto);

    @Select("select * from member where email=#{email}")
    Member findMemberByEamilThisIsReal(String email);

    @Select("select count(*) from member")
    int getMemberTotalCount();

    List<Member> getMemberPage(RowBounds rowBounds);

    @Update("update member set email=#{email}, nickname=#{nickname}, phone=#{phone}, birthday=#{birthday}, gender=#{gender} where id=#{id}")
    int updateMemberByAdmin(UpdateMemberDto memberUpdate);

    @Delete("delete authorities where member_id=#{id}")
    void deleteAuthorities(int id);

    @Select("select * from member where id=#{writerId}")
    Member findById(int writerId);

    @Select("select count(*) from follow where follower= #{id}")
    int findFollowerCountByMemberId(int id);
    @Select("select count(*) from follow where followee= #{id}")
    int findFolloweeCountByMemberId(int id);
    @Select("select * from member where username=#{username}")
    Member findByUsername(String username);

    @Select("SELECT *\n" +
            "FROM ATTACHMENT_PROFILE ap\n" +
            "         LEFT JOIN PROFILE p ON ap.PROFILE_ID = p.ID\n" +
            "         JOIN ATTACHMENT a ON ap.ATTACHMENT_ID = a.ID\n" +
            "         JOIN MEMBER m ON p.MEMBER_ID = m.ID\n" +
            "WHERE PHONE LIKE '%' || #{keyword} || '%' OR USERNAME LIKE '%' || #{keyword} || '%' OR NICKNAME LIKE '%' || #{keyword} || '%' OR EMAIL LIKE '%' || #{keyword} || '%'")
    List<SearchMemberDto> findMemberByKeyword(@Param("keyword") String keyword);



}

