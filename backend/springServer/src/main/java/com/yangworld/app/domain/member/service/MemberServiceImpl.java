package com.yangworld.app.domain.member.service;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.member.dto.*;
import com.yangworld.app.domain.member.entity.Authority;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public PrincipalDetails loadUserByUsername(String username) {
        PrincipalDetails principalDetails = new PrincipalDetails();
        if(principalDetails == null)
            throw new UsernameNotFoundException(username);
        return principalDetails;
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
    public int memberTotalCount() {
        return memberRepository.getMemberTotalCount();
    }

    @Override
    public List<Member> getMemberPage(int pageNo, int pageSize) {
        int offset = (pageNo-1)*pageSize;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        List<Member> memberList = memberRepository.getMemberPage(rowBounds);
        return memberList;
    }

    @Override
    public int updateMemberByAdmin(UpdateMemberDto memberUpdate) {
        log.info("update ={} ", memberUpdate);
        int result = memberRepository.updateMemberByAdmin(memberUpdate);
        memberRepository.deleteAuthorities(memberUpdate.getId());
        List<String> authorityList = new ArrayList<>();
        for (AuthorityDto authority : memberUpdate.getAuthorities()) {
            authorityList.add(authority.getAuthority());
        }
        result += memberRepository.insertAuthorities(memberUpdate.getId(), authorityList);
        return result;
    }

    @Override
    public List<MonthlyMemberCountDto> findMonthlyMemberCount() {
        return memberRepository.findMonthlyMemberCount();
    }

    @Override
    public List<MonthlyMemberCountDto> findMonthlyDeletedMemberCount() {
        return memberRepository.findMonthlyDeletedMemberCount();
    }

    @Override
    public List<OAuthMemberDto> findOAuthMemberCount() {
        return memberRepository.findOAuthMemberCount();
    }
}
