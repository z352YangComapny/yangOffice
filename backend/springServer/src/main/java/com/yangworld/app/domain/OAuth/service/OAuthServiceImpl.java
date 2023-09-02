package com.yangworld.app.domain.OAuth.service;

import com.google.gson.Gson;
import com.yangworld.app.common.jwt.JwtProvider;
import com.yangworld.app.common.redis.entity.RefreshToken;
import com.yangworld.app.common.redis.service.RedisService;
import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.OAuth.provider.kakao.KaKaoMemberInfo;
import com.yangworld.app.domain.OAuth.dto.OAuthDto;
import com.yangworld.app.domain.OAuth.provider.kakao.KakaoOAuth;
import com.yangworld.app.domain.member.dto.SignUpDto;
import com.yangworld.app.domain.member.entity.Gender;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.repository.MemberRepository;
import com.yangworld.app.domain.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Service
@Slf4j
public class OAuthServiceImpl implements OAuthService {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberService memberService;

    @Autowired
    RedisService redisService;

    JwtProvider jwtProvider = new JwtProvider();

    @Override
    @Transactional
    public String[] kakao(OAuthDto oAuthDto) {
        String[] array = new String[2];

        String postURL = "https://kauth.kakao.com/oauth/token";
        String grant_type = "authorization_code";
        String client_id = "a7b86ff96d50db1785b75938758aeb44";
        String redirect_uri = "http://localhost:3000/signin";
        String code = oAuthDto.getKakaoCode();
        String client_secret = "vU2DG59HgaZ8s6nmIf7kkfzWnWYCkqmX";

        log.info("kakaoCode = {}", code);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", grant_type);
        requestBody.add("client_id", client_id);
        requestBody.add("redirect_uri", redirect_uri);
        requestBody.add("code", code);
        requestBody.add("client_secret",client_secret);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        log.info("requestEntity = {}", requestEntity);

        ResponseEntity<KakaoOAuth> kakaoOAuthResponseEntity
                = restTemplate.exchange(
                postURL,
                HttpMethod.POST,
                requestEntity,
                KakaoOAuth.class
        );

        log.info("Response Status Code: {}", kakaoOAuthResponseEntity.getStatusCode());
        log.info("Response Body: {}", kakaoOAuthResponseEntity.getBody());

        String accessToken = kakaoOAuthResponseEntity.getBody().getAccess_token();
        KaKaoMemberInfo kakaoMemberInfo = getKakaoMemberInfo(accessToken);

        Member member = memberRepository.findMemberByEamilThisIsReal(kakaoMemberInfo.getKakao_account().getEmail());
        if(member != null){
            String _accessToken = jwtProvider.createAccessToken(member);
            String refreshToken = jwtProvider.createRefreshToken(member);
            RefreshToken refreshTokenObj = RefreshToken.builder()
                    .userId(member.getId())
                    .tokenVal(refreshToken).build();
            redisService.setData(refreshTokenObj);
            array[1] = _accessToken;
            return array;
        }
        String id = kakaoMemberInfo.getId()+"";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date today = new Date(calendar.getTime().getTime());

        SignUpDto signUpDto = SignUpDto.builder()
                .username(id+"@KAKAO")
                .password(passwordEncoder.encode(kakaoMemberInfo.getProperties().getNickname()))
                .name("id@KAKAO")
                .nickname(kakaoMemberInfo.getKakao_account().getProfile().getNickname())
                .email(kakaoMemberInfo.getKakao_account().getEmail())
                .gender("M")
                .birthday("20230101")
                .phone(id)
                .provider("KAKAO")
                .build();

        memberService.insertMember(signUpDto);
        log.info("{}",signUpDto);

        String _accessToken = jwtProvider.createAccessToken(signUpDto);
        String refreshToken = jwtProvider.createRefreshToken(signUpDto);
        RefreshToken refreshTokenObj = RefreshToken.builder()
                .userId(signUpDto.getId())
                .tokenVal(refreshToken).build();
        redisService.setData(refreshTokenObj);

        array[0]=_accessToken;
        return array;
    }

    public KaKaoMemberInfo getKakaoMemberInfo(String accessToken) {
        String userInfoUrl = "https://kapi.kakao.com/v2/user/me";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer "+accessToken);
        HttpEntity httpEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> resp = restTemplate.exchange(userInfoUrl , HttpMethod.POST , httpEntity, String.class);
        log.info("info={}", resp);

        Gson gson = new Gson();
        KaKaoMemberInfo kaKaoMemberInfo = gson.fromJson(resp.getBody(), KaKaoMemberInfo.class);

        log.info("kaKaoMember={}", kaKaoMemberInfo);

        return kaKaoMemberInfo;
    }

}
