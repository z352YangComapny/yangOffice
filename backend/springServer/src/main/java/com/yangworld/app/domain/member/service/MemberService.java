package com.yangworld.app.domain.member.service;

import com.yangworld.app.domain.member.dto.SignUpDto;

public interface MemberService {
    int insertMember(SignUpDto signUpDto);
}
