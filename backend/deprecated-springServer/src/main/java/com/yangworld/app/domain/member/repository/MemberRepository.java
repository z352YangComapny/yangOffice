package com.yangworld.app.domain.member.repository;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.member.dto.*;
import org.apache.ibatis.annotations.*;
import com.yangworld.app.domain.member.entity.Member;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface MemberRepository {
    @Insert("insert into member values(seq_member_id.nextval, #{username},  #{name}, #{password}, #{nickname}, #{birthday}, #{gender}, #{phone}, #{email, jdbcType=VARCHAR}, #{provider}, default)")
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


    @Select("WITH Months AS (\n" +
            "    SELECT LEVEL AS month\n" +
            "    FROM DUAL\n" +
            "    CONNECT BY LEVEL <= 12\n" +
            ")\n" +
            "SELECT TO_CHAR(Months.month, 'FM00') AS month,\n" +
            "       COUNT(member.id) AS member_count\n" +
            "FROM Months\n" +
            "         LEFT JOIN member ON TO_CHAR(member.reg_date, 'MM') = TO_CHAR(Months.month, 'FM00')\n" +
            "GROUP BY Months.month\n" +
            "ORDER BY Months.month")
    List<MonthlyMemberCountDto> findMonthlyMemberCount();

    @Select("WITH Months AS (\n" +
            "    SELECT LEVEL AS month\n" +
            "    FROM DUAL\n" +
            "    CONNECT BY LEVEL <= 12\n" +
            ")\n" +
            "SELECT TO_CHAR(Months.month, 'FM00') AS month,\n" +
            "       COALESCE(COUNT(DELETED_MEMBER.username), 0) AS member_count\n" +
            "FROM Months\n" +
            "         LEFT JOIN deleted_member\n" +
            "                   ON TO_CHAR(deleted_member.deleted_date, 'MM') = TO_CHAR(Months.month, 'FM00')\n" +
            "GROUP BY Months.month\n" +
            "ORDER BY Months.month")
    List<MonthlyMemberCountDto> findMonthlyDeletedMemberCount();

    @Select("SELECT provider, COUNT(*) AS member_count\n" +
            "FROM member\n" +
            "GROUP BY provider\n" +
            "ORDER BY provider")
    List<OAuthMemberDto> findOAuthMemberCount();

    @Select("select * from member where username=#{username}")
    Member findByUsername(String username);

    @Select("SELECT *\n" +
            "FROM ATTACHMENT_PROFILE ap\n" +
            "         LEFT JOIN PROFILE p ON ap.PROFILE_ID = p.ID\n" +
            "         JOIN ATTACHMENT a ON ap.ATTACHMENT_ID = a.ID\n" +
            "         JOIN MEMBER m ON p.MEMBER_ID = m.ID\n" +
            "WHERE PHONE LIKE '%' || #{keyword} || '%' OR USERNAME LIKE '%' || #{keyword} || '%' OR NICKNAME LIKE '%' || #{keyword} || '%' OR EMAIL LIKE '%' || #{keyword} || '%'")
    List<SearchMemberDto> findMemberByKeyword(@Param("keyword") String keyword);


    @Select("select * from member where nickname = #{nickname}")
    Member findByNickname(String nickname);
    @Select("select * from member where phone = #{phone}")
    Member findByPhone(String phone);
}

