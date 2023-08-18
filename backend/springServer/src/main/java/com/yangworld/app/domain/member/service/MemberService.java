package com.yangworld.app.domain.member.service;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.member.dto.FindIdDto;
import com.yangworld.app.domain.member.dto.FollowDto;
import com.yangworld.app.domain.member.dto.SignUpDto;
import com.yangworld.app.domain.member.dto.UpdateDto;
import com.yangworld.app.domain.member.entity.Member;

public interface MemberService {
    int insertMember(SignUpDto signUpDto);

    int updateMember(UpdateDto updateDto, String username);

//    PrincipalDetails loadUserByUsername(String username);

    int deleteMember(String username);

    int insertFollowee(FollowDto followDto);

    int deleteFollowee(FollowDto unfollow);

    String findMemberByEmail(FindIdDto findIdDto);

	Member findByUsername(int writerId);
}
