package com.ssoystory.memberservice.domain.member.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ssoystory.memberservice.common.jwt.JwtProvider;
import com.ssoystory.memberservice.common.jwt.TokenClaims;
import com.ssoystory.memberservice.common.redis.entity.RefreshToken;
import com.ssoystory.memberservice.common.redis.service.RedisService;
import com.ssoystory.memberservice.domain.member.dto.MemberUpdateDto;
import com.ssoystory.memberservice.domain.member.dto.SignInDto;
import com.ssoystory.memberservice.domain.member.dto.SignUpDto;
import com.ssoystory.memberservice.domain.member.entity.Authority;
import com.ssoystory.memberservice.domain.member.entity.Member;
import com.ssoystory.memberservice.domain.member.entity.Provider;
import com.ssoystory.memberservice.domain.member.repository.MemberRepository;
import com.ssoystory.memberservice.exception.UnAuthorizedError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@PropertySource("classpath:JwtConfig.properties")
@Transactional
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
    public void updateMember(MemberUpdateDto memberUpdateDto, String jwtToken) throws RuntimeException/* 호출 받은 곳에 날림 == 호출한 너가 처리해라 */ {
        try {
            jwtVerifier.verify(jwtToken);
        }catch (RuntimeException e){
            log.info("Error = {}" ,e);
            return ;
        }
        DecodedJWT decodedJWT = JWT.decode(jwtToken); // 이 안에 요청자의 ID (Long) 자기가 인증이 성공했을때만 보유.
        Optional<Member> member = memberRepository.findById(Long.parseLong(decodedJWT.getSubject())); // 요청자의 정보를 꺼내오겠다. == 요청자는 자기것만 수정가능. (인가처리)
        if (member.isEmpty()){ // 요청자의 정보가 없으면.
            throw new UnAuthorizedError("Target ID = {"+memberUpdateDto.getId()+"} , RequesterID = {"+decodedJWT.getSubject()+"}"); // Error Throws
        } else { // 정보 1) 요청자의 정보가 있다.(In DataBase) <= 덮어쓰기. 정보2) 요청할 때 입력값들도 있어.(Input Values)
            Member existingMember = member.get(); // 상자 안에 있는 값을 가져오는 것. 상자 안에 사과가 있음 .get >> 사과를 가져옴.
            existingMember.setId(memberUpdateDto.getId()); // ID == null 이되면서 JPA 가 NULL 이됨.
            existingMember.setUsername(memberUpdateDto.getUsername());
            existingMember.setName(memberUpdateDto.getName());
            existingMember.setPassword(passwordEncoder.encode(memberUpdateDto.getPassword()));
            existingMember.setNickname(memberUpdateDto.getNickname());
            existingMember.setBirthday(memberUpdateDto.getBirthday());
            existingMember.setGender(memberUpdateDto.getGender());
            existingMember.setPhone(memberUpdateDto.getPhone());
            existingMember.setEmail(memberUpdateDto.getEmail());
            memberRepository.save(existingMember); // Jpa save 때리면됨.
        }
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
