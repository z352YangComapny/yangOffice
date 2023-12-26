package com.yangworld.app.domain.OAuth.service;

import com.google.gson.Gson;
import com.yangworld.app.common.jwt.JwtProvider;
import com.yangworld.app.common.redis.entity.RefreshToken;
import com.yangworld.app.common.redis.service.RedisService;
import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.OAuth.dto.NaverDto;
import com.yangworld.app.domain.OAuth.provider.kakao.KaKaoMemberInfo;
import com.yangworld.app.domain.OAuth.dto.OAuthDto;
import com.yangworld.app.domain.OAuth.provider.kakao.KakaoOAuth;
import com.yangworld.app.domain.OAuth.provider.naver.NaverMemberInfo;
import com.yangworld.app.domain.OAuth.provider.naver.NaverOauth;
import com.yangworld.app.domain.member.dto.SignUpDto;
import com.yangworld.app.domain.member.entity.Gender;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.repository.MemberRepository;
import com.yangworld.app.domain.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


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

    @Override
    public String[] naver(OAuthDto oauthDto) {
        String[] array = new String[2];

        String code = oauthDto.getNaverCode();
        String state =oauthDto.getNaverState();
        // 요청 uri 정의
        String TOKEN_URI = "https://nid.naver.com/oauth2.0/token";
        String CLIENT_ID = "qMXozarpkt37ZQhal0iY";
        String grant_type = "authorization_code";
        String CLIENT_SECRET = "2FzvJIogTd";

        String tokenUri = TOKEN_URI
                +"?grant_type=authorization_code"
                +"&client_id=" +CLIENT_ID
                +"&client_secret=" +CLIENT_SECRET
                +"&code=" +code
                +"&state=" + state;

        // 결과 값을 담을 객체를 생성
        RestTemplate restTemplate = new RestTemplate();
        log.info("토큰 요청 들어오나?");
        log.info("code={}", code);

        // 요청 header, 사용자 입력값
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<?> responseEntity
                = restTemplate.exchange(URI.create(tokenUri), HttpMethod.GET, httpEntity, Map.class);

        log.info("getToken@response={}", responseEntity);

        Map<String, Object> tokens = (Map<String, Object>)responseEntity.getBody();
        log.info("token={}", tokens);

        String accessToken = (String)tokens.get("access_token");
        String refreshToken = (String)tokens.get("refresh_token");
        log.info("accesstoken = {}", accessToken);
        log.info("refresh ={}", refreshToken);


        // 사용자 정보 요청하기
        httpHeaders.setBearerAuth(accessToken);
        HttpEntity<HttpHeaders> httpEntity2 = new HttpEntity<>(httpHeaders);

        String USER_INFO_URI = "https://openapi.naver.com/v1/nid/me";

        ResponseEntity<Map<String, Object>> responseEntity2
                = restTemplate.exchange(URI.create(USER_INFO_URI),
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<Map<String, Object>>(){});

        log.info("getProfile@responseEntity={}", responseEntity2);
        // getProfile의 응답 값
        Map<String, Object> attribute = responseEntity2.getBody();
        log.info("attribute = {}", attribute);

        Map<String, Object> response = ( Map<String, Object>)attribute.get("response");
        log.info("response={}", response);

        Member member = memberRepository.findMemberByEamilThisIsReal((String)response.get("email"));
        if(member != null){
            String _accessToken = jwtProvider.createAccessToken(member);
            String _refreshToken = jwtProvider.createRefreshToken(member);
            RefreshToken refreshTokenObj = RefreshToken.builder()
                    .userId(member.getId())
                    .tokenVal(_refreshToken).build();
            redisService.setData(refreshTokenObj);
            array[1] = _accessToken;
            return array;
        }
        String id= (String)response.get("id");
        String username = id.substring(0,12) + "@naver";
        String name = (String)response.get("name");
        String email = (String)response.get("email");
        String gender = (String)response.get("gender");
        String birthdate = ((String)response.get("birthday")).replace("-", "");
        String birthyear = (String)response.get("birthyear");
        String _birthday = birthyear+birthdate;
        log.info("birthday={}", _birthday);
        String nickname = (String)response.get("nickname");

//       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//      LocalDate birthday = LocalDate.parse(_birthday, formatter);

        String phone = ((String)response.get("mobile")).replace("-", "");

        SignUpDto signUpDto = SignUpDto.builder()
                .username(username)
                .password(passwordEncoder.encode("1234"))
                .name(name)
                .email(email)
                .birthday(_birthday)
                .gender(gender)
                .phone(phone)
                .nickname(nickname)
                .provider("NAVER")
                .build();

        log.info("{}",signUpDto);
        memberService.insertMember(signUpDto);
        String _accessToken = jwtProvider.createAccessToken(signUpDto);
        String _refreshToken = jwtProvider.createRefreshToken(signUpDto);
        RefreshToken refreshTokenObj = RefreshToken.builder()
                .userId(signUpDto.getId())
                .tokenVal(_refreshToken).build();
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
