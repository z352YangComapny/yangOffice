package com.ssoystory.memberservice.domain.member.service;

import com.ssoystory.memberservice.common.jwt.JwtProvider;
import com.ssoystory.memberservice.common.jwt.TokenClaims;
import com.ssoystory.memberservice.common.redis.entity.RefreshToken;
import com.ssoystory.memberservice.common.redis.service.RedisService;
import com.ssoystory.memberservice.domain.member.dto.SignInDto;
import com.ssoystory.memberservice.domain.member.dto.SignUpDto;
import com.ssoystory.memberservice.domain.member.entity.Authority;
import com.ssoystory.memberservice.domain.member.entity.Member;
import com.ssoystory.memberservice.domain.member.entity.Provider;
import com.ssoystory.memberservice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final RedisService redisService;
    @Autowired
    private final JwtProvider jwtProvider;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Member signup(SignUpDto signUpDto) throws RuntimeException {
        Member member = Member.builder()
                .username(signUpDto.getUsername())
                .name(signUpDto.getName())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .nickname(signUpDto.getNickname())
                .birthday(signUpDto.getBirthday())
                .gender(signUpDto.getGender())
                .phone(signUpDto.getPhone())
                .email(signUpDto.getEmail())
                .authorities(new ArrayList<Authority>())
                .provider(Provider.Yang)
                .build();
        Authority authority = Authority.builder()
                .authority("ROLE_USER")
                .member(member)
                .build();
        member.getAuthorities().add(authority);
        return memberRepository.save(member);
    }

    @Override
    public Optional<Member> findById(long id) {
        return memberRepository.findById(id);
    }

    @Override
    public String signIn(SignInDto signInDto) {
        Optional<Member> member = memberRepository.findMemberByUsername(signInDto.getUsername());
        if(member.isPresent() && passwordEncoder.matches(signInDto.getPassword(),member.get().getPassword())){
            log.info("{}",member.get());
            TokenClaims tokenClaims = TokenClaims.builder()
                    .Id(member.get().getId())
                    .username(member.get().getUsername())
                    .nickname(member.get().getNickname())
                    .authorities(member.get().getAuthorities().get(0).getAuthority())
                    .build();

            String refreshToken = jwtProvider.refreshTokenProvider(tokenClaims);
            RefreshToken refreshTokenObj = RefreshToken.builder()
                    .userId(tokenClaims.getId().intValue())
                    .tokenVal(refreshToken).build();

            redisService.setData(refreshTokenObj);
            return jwtProvider.accessTokenProvider(tokenClaims);
        }
        return null;
    }
}
