package com.ssoystory.memberservice.domain.member.service;

import com.ssoystory.memberservice.domain.member.dto.SignInDto;
import com.ssoystory.memberservice.domain.member.dto.SignUpDto;
import com.ssoystory.memberservice.domain.member.entity.Member;

import java.util.Optional;

public interface MemberService {
    Member signup(SignUpDto signUpDto);

    Optional<Member> findById(long id);

    String signIn(SignInDto signInDto);
}
