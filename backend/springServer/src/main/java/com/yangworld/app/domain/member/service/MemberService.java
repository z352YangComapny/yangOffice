package com.yangworld.app.domain.member.service;

import com.yangworld.app.domain.member.dto.FollowDto;
import com.yangworld.app.domain.member.dto.SignUpDto;
import com.yangworld.app.domain.member.dto.UpdateDto;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.entity.MemberDetails;

import java.util.List;
import java.util.Map;

public interface MemberService {
    int insertMember(SignUpDto signUpDto);

    int updateMember(UpdateDto updateDto, String username);

    int deleteMember(String username);

    int insertFollowee(FollowDto followDto);

    int deleteFollowee(FollowDto unfollowDto);

	Member findById(int writerId);
	
    Member findByNickname(String nickname);

    Member findByPhone(String phone);

    Member findMemberByEmail(String email);

    int resetPassword(String newPassword, String username);


    List<Member> findAllMember(Map<String, Object> params);

    List<Member> findMemberByText(String inputText,  Map<String, Object> params);

    List<FollowDto> findFollowee(int id);

    Member findMemberbyUsername(String username);

    int findTotalMemberCount();

    int findTotalMemberCountByInput(String inputText);
}
