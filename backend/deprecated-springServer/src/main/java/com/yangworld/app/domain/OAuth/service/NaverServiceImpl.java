package com.yangworld.app.domain.OAuth.service;

import com.yangworld.app.domain.OAuth.dto.NaverDto;
import com.yangworld.app.domain.OAuth.provider.kakao.KakaoOAuth;
import com.yangworld.app.domain.OAuth.provider.naver.NaverOauth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import java.net.URI;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class NaverServiceImpl implements NaverService {

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String CLIENT_SECRET;

    @Value("${spring.security.oauth2.client.provider.naver.authorization-uri}")
    private String AUTHORIZE_URI;

    @Value("${spring.security.oauth2.client.registration.naver.redirect-uri}")
    private String REDIRECT_URI;

    @Value("${spring.security.oauth2.client.registration.naver.authorization-grant-type}")
    private String AUTHORIZATION_GRANT_TYPE;

    @Value("${spring.security.oauth2.client.registration.naver.scope}")
    private String SCOPE;

    @Value("${spring.security.oauth2.client.registration.naver.client-name}")
    private String CLIENT_NAME;

    @Value("${spring.security.oauth2.client.provider.naver.authorization-uri}")
    private String AUTHORIZAION_URI;

    @Value("${spring.security.oauth2.client.provider.naver.token-uri}")
    private String TOKEN_URI;

    @Value("${spring.security.oauth2.client.provider.naver.user-info-uri}")
    private String USER_INFO_URI;

    @Value("${spring.security.oauth2.client.provider.naver.user-name-attribute}")
    private String USER_NAME_ATTRIBUTE;

    //private String state = "ssoystory";

//    @Override
//    public String getAuthorizeUri() {
//        return AUTHORIZAION_URI
//                +"?response_type=code"
//                +"&client_id=" +CLIENT_ID
//                +"&redirect_uri=" +REDIRECT_URI
//                +"&state=" + state;
//    }


    public Map<String, Object> getTokens(String code, String state) {
        // 결과 값을 담을 객체를 생성
        RestTemplate restTemplate = new RestTemplate();
        log.info("토큰 요청 들어오나?");
        log.info("code={}", code);

        // 요청 header, 사용자 입력값
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(httpHeaders);

        // 요청 uri 정의
        String tokenUri = TOKEN_URI
                +"?grant_type=authorization_code"
                +"&client_id=" +CLIENT_ID
                +"&client_secret=" +CLIENT_SECRET
                +"&code=" +code
                +"&state=" + "ssoystory";

        ResponseEntity<?> responseEntity
                = restTemplate.exchange(URI.create(tokenUri), HttpMethod.GET, httpEntity, Map.class);

        log.info("getToken@response={}", responseEntity);

        return (Map<String, Object>)responseEntity.getBody();
    }

    @Override
    public Map<String, Object> getTokens(String code) {
        return null;
    }

    // accessToken으로 사용자 정보 요청하기
    @Override
    public Map<String, Object> getProfile(String accessToken) {
        log.info("accesstoken={}", accessToken);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(accessToken);
        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(httpHeaders);
        String uri = USER_INFO_URI;

        ResponseEntity<Map<String, Object>> responseEntity
                = restTemplate.exchange(URI.create(uri),
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<Map<String, Object>>() {});

        log.info("getProfile@responseEntity={}", responseEntity);

        return responseEntity.getBody();
    }

    @Override
    public String[] naver(NaverDto naverDto) {
        String[] array = new String[2];

        String code = naverDto.getNaverCode();
        String state =naverDto.getState();
        // 요청 uri 정의
        String tokenUri = TOKEN_URI
                +"?grant_type=authorization_code"
                +"&client_id=" +CLIENT_ID
                +"&client_secret=" +CLIENT_SECRET
                +"&code=" +code
                +"&state=" + state;

        String tokenURL = TOKEN_URI;
        String grant_type = "authorization_code";
        String client_id = CLIENT_ID;
       // String redirect_uri = "http://localhost:3000/signin";
        String client_secret = CLIENT_SECRET;
        String _state = state;

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", grant_type);
        requestBody.add("client_id", client_id);
        requestBody.add("client_secret", client_secret);
        requestBody.add("code", code);
        requestBody.add("state", _state);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        log.info("naverCode = {}", code);
        log.info("nvaerState = {}", state);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        log.info("requestEntity = {}", requestEntity);

        ResponseEntity<NaverOauth> NaverOAuthResponseEntity
                = restTemplate.exchange(
                URI.create(tokenUri),
                HttpMethod.GET,
                requestEntity,
                NaverOauth.class
        );

        log.info("Response Status Code: {}", NaverOAuthResponseEntity.getStatusCode());
        log.info("Response Body: {}", NaverOAuthResponseEntity.getBody());

        String accessToken = NaverOAuthResponseEntity.getBody().getAccess_token();
        log.info("accessToken@naver = {}", accessToken);

        String uri = USER_INFO_URI;

        return new String[0];
    }
}
