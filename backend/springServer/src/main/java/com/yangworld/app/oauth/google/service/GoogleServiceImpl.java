package com.yangworld.app.oauth.google.service;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.config.auth.PrincipalDetailsService;
import com.yangworld.app.domain.member.dto.SignUpDto;
import com.yangworld.app.domain.member.repository.MemberRepository;
import com.yangworld.app.oauth.google.dto.GoogleInfoResponse;
import com.yangworld.app.oauth.google.dto.GoogleRequest;
import com.yangworld.app.oauth.google.dto.GoogleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class GoogleServiceImpl implements GoogleService {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String CLIENT_ID;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String SECRET;
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String REDIRECT_URI;
    @Value("${spring.security.oauth2.client.registration.google.scope}")
    private String SCOPE;
    @Value("${spring.security.oauth2.client.provider.google.token-uri}")
    private String TOKEN_URI;

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private PrincipalDetailsService principalDetailsService;

    // 구글 로그인
    @Override
    public String getAuthorizeUri() {
        log.info("Client_URI = {}", CLIENT_ID);
//        String url = "https://accounts.google.com/o/oauth2/v2/auth?client_id="
//                + ClIENT_ID
//                + "&redirect_uri=" + REDIRECT_URI
//                + "&response_type=code"
//                + "&scope=" + SCOPE;

        String url = "https://accounts.google.com/o/oauth2/v2/auth?scope=email profile&access_type=offline&include_granted_scopes=true&response_type=code&state=state_parameter_passthrough_value&redirect_uri="
                + REDIRECT_URI + "&client_id=" + CLIENT_ID;


        log.info("url = {}", url);
        return url;
    }

    @Override
    public Map<String, Object> getTokens(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(httpHeaders);
        GoogleRequest googleOAuthRequestParam = GoogleRequest
                .builder()
                .clientId(CLIENT_ID)
                .clientSecret(SECRET)
                .code(code)
                .redirectUri(REDIRECT_URI)
                .grantType("authorization_code").build();

        ResponseEntity<GoogleResponse> response = restTemplate.postForEntity("https://oauth2.googleapis.com/token",
                googleOAuthRequestParam, GoogleResponse.class);

        GoogleResponse googleResponse = response.getBody();

        Map<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("access_token", googleResponse.getAccess_token());
        tokenMap.put("expires_in", googleResponse.getExpires_in());
        // Add other fields as needed

        return tokenMap;
    }


    public Map<String, Object> getGoogleInfo(String accessToken) {


        // 토큰
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(accessToken);

        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(httpHeaders);
        String uri = "https://www.googleapis.com/oauth2/v2/userinfo";

        ResponseEntity<Map<String, Object>> responseEntity =
                restTemplate.exchange(URI.create(uri), HttpMethod.GET, httpEntity,
                        new ParameterizedTypeReference<Map<String, Object>>() {
                        });

        return responseEntity.getBody();
    }


}




