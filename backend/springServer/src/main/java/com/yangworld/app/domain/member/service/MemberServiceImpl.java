package com.yangworld.app.domain.member.service;

import com.yangworld.app.domain.member.dto.SignUpDto;
import com.yangworld.app.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public int insertMember(SignUpDto signUpDto) {
        return memberRepository.insertMember(signUpDto);
    }
}
