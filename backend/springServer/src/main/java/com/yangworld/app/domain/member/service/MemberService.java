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

    int insertFollowee(FollowDto followDto);

    int deleteFollowee(FollowDto unfollow);

    int memberTotalCount();

    List<Member> getMemberPage(int pageNo, int pageSize);

    int updateMemberByAdmin(UpdateMemberDto memberUpdate);

    List<MonthlyMemberCountDto> findMonthlyMemberCount();

    List<MonthlyMemberCountDto> findMonthlyDeletedMemberCount();

    List<OAuthMemberDto> findOAuthMemberCount();
}
