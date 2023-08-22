package com.yangworld.app.domain.member.service;

import com.yangworld.app.domain.member.dto.FollowDto;
import com.yangworld.app.domain.member.dto.SignUpDto;
import com.yangworld.app.domain.member.dto.UpdateDto;
import com.yangworld.app.domain.member.entity.Member;

public interface MemberService {
    int insertMember(SignUpDto signUpDto);

    int updateMember(UpdateDto updateDto, String username);

    int deleteMember(String username);

    int insertFollowee(FollowDto followDto);

    int deleteFollowee(FollowDto unfollow);

	Member findById(int writerId);

    Member findByNickname(String nickname);

    Member findByPhone(String phone);

    Member findMemberByEmail(String email);

    int resetPassword(String newPassword, String username);

}
