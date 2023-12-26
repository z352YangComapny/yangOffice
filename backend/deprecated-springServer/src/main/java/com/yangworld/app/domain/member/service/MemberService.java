package com.yangworld.app.domain.member.service;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.member.dto.*;
import com.yangworld.app.domain.member.entity.Member;

import java.util.List;

public interface MemberService {
    int insertMember(SignUpDto signUpDto);

    int updateMember(UpdateDto updateDto, String username);

    PrincipalDetails loadUserByUsername(String username);

    int deleteMember(String username);

    int insertFollowee(PrincipalDetails principal, String hostname);

    int deleteFollowee(PrincipalDetails principal, String hostname);

    int memberTotalCount();

    List<Member> getMemberPage(int pageNo, int pageSize);

    int updateMemberByAdmin(UpdateMemberDto memberUpdate);

    List<MonthlyMemberCountDto> findMonthlyMemberCount();

    List<MonthlyMemberCountDto> findMonthlyDeletedMemberCount();

    List<OAuthMemberDto> findOAuthMemberCount();

    List<SearchMemberDto> searchMember(String keyword);

    Member findByNickname(String nickname);

    Member findByPhone(String phone);
}
