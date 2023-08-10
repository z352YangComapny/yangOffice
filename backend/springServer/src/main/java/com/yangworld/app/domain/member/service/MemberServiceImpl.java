package com.yangworld.app.domain.member.service;

import com.yangworld.app.domain.member.dto.SignUpDto;
import com.yangworld.app.domain.member.entity.Authority;
import com.yangworld.app.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        authorityList.add(Authority.ROLE_ADMIN.name());
        authorityList.add(Authority.ROLE_MANAGER.name());
        int result = memberRepository.insertAuthorities(signUpDto.getId(), authorityList);
        return result;
    }

}
