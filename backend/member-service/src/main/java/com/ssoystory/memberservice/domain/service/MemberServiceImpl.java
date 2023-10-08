package com.ssoystory.memberservice.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ssoystory.memberservice.common.jwt.JwtProvider;
import com.ssoystory.memberservice.common.jwt.TokenClaims;
import com.ssoystory.memberservice.common.redis.entity.RefreshToken;
import com.ssoystory.memberservice.common.redis.service.RedisService;
import com.ssoystory.memberservice.domain.dto.MemberUpdateDto;
import com.ssoystory.memberservice.domain.dto.SignInDto;
import com.ssoystory.memberservice.domain.dto.SignUpDto;
import com.ssoystory.memberservice.domain.entity.Authority;
import com.ssoystory.memberservice.domain.entity.Member;
import com.ssoystory.memberservice.domain.entity.Provider;
import com.ssoystory.memberservice.domain.repository.MemberRepository;
import com.ssoystory.memberservice.exception.UnAuthorizedError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@PropertySource("classpath:JwtConfig.properties")
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final RedisService redisService;
    private final JwtProvider jwtProvider;
    private final JWTVerifier jwtVerifier;
    private final String SECRET_KEY;
    private final Algorithm algorithm;
    private final PasswordEncoder passwordEncoder;


    public MemberServiceImpl(@Autowired MemberRepository memberRepository,
                             @Autowired RedisService redisService,
                             @Autowired JwtProvider jwtProvider,
                             @Autowired PasswordEncoder passwordEncoder,
                             @Value("${jwt.properties.secret}") String SECRET_KEY
    ) {
        this.memberRepository = memberRepository;
        this.redisService = redisService;
        this.jwtProvider = jwtProvider;
        this.SECRET_KEY = SECRET_KEY;
        this.algorithm = Algorithm.HMAC512(SECRET_KEY);
        this.jwtVerifier = JWT.require(Algorithm.HMAC512(SECRET_KEY)).acceptLeeway(5).build();
        this.passwordEncoder = passwordEncoder;
    }



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

    @Override
    public void updateMember(MemberUpdateDto memberUpdateDto, String jwtToken) throws RuntimeException {
        try {
            jwtVerifier.verify(jwtToken);
        }catch (RuntimeException e){
            log.info("Error = {}" ,e);
        }
        DecodedJWT decodedJWT = JWT.decode(jwtToken);
        Optional<Member> member = findById(Long.parseLong(decodedJWT.getSubject()));
        if (member.isEmpty()){
            throw new UnAuthorizedError("Target ID = {"+memberUpdateDto.getId()+"} , RequesterID = {"+decodedJWT.getSubject()+"}");
        } else {
            Member existingMember = member.get();
                    existingMember.setId(memberUpdateDto.getId());
                    existingMember.setUsername(memberUpdateDto.getUsername());
                    existingMember.setName(memberUpdateDto.getName());
                    existingMember.setPassword(passwordEncoder.encode(memberUpdateDto.getPassword()));
                    existingMember.setNickname(memberUpdateDto.getNickname());
                    existingMember.setBirthday(memberUpdateDto.getBirthday());
                    existingMember.setGender(memberUpdateDto.getGender());
                    existingMember.setPhone(memberUpdateDto.getPhone());
                    existingMember.setEmail(memberUpdateDto.getEmail());
            memberRepository.save(existingMember);
        }
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

}
