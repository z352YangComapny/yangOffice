package com.yangworld.app.domain.member.service;

import com.yangworld.app.domain.member.dto.FollowDto;
import com.yangworld.app.domain.member.dto.SignUpDto;
import com.yangworld.app.domain.member.dto.UpdateDto;
import com.yangworld.app.domain.member.entity.Authority;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberRepository memberRepository;


    @Override
    public int insertMember(SignUpDto signUpDto) {
        List<String> authorityList = new ArrayList<>();
        int id = memberRepository.insertMember(signUpDto);
        authorityList.add(Authority.ROLE_USER.name());
//        authorityList.add(Authority.ROLE_ADMIN.name());
//        authorityList.add(Authority.ROLE_MANAGER.name());
        int result = memberRepository.insertAuthorities(signUpDto.getId(), authorityList);
        return result;
    }

    @Override
    public int updateMember(UpdateDto updateDto, String username) {
        return memberRepository.updateMember(updateDto, username);
    }

    @Override
    public int deleteMember(String username) {
        return memberRepository.deleteMember(username);
    }

    @Override
    public int insertFollowee(FollowDto followDto) {
        return memberRepository.insertFollowee(followDto);
    }

    @Override
    public int deleteFollowee(FollowDto unfollow) {
        return memberRepository.deleteFollowee(unfollow);
    }

	@Override
	public Member findById(int writerId) {
		return memberRepository.findById(writerId);
	}

    @Override
    public Member findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    @Override
    public Member findByPhone(String phone) {
        return memberRepository.findByPhone(phone);
    }

    @Override
    public Member findMemberByEmail(String email) {
        return memberRepository.findMemberByEmail(email);
    }

    @Override
    public int resetPassword(String newPassword, String username) {
        return memberRepository.resetPassword(newPassword, username);
    }


}
