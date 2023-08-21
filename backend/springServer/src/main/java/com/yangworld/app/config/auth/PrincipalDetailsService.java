package com.yangworld.app.config.auth;

import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("init loadUserByUsername username={}",username);
        PrincipalDetails principalDetails = memberRepository.loadUserByUsername(username);
        log.info("member = {}", principalDetails);
//        if(principalDetails != null){
//            return principalDetails;
//        }
//        return null;
        if(principalDetails == null)
            throw new UsernameNotFoundException(username);
        return principalDetails;
    }
}
