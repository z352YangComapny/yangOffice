package com.yangworld.app.oauth.kakao.service;

import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.oauth.dto.GoogleInfoResponse;
import com.yangworld.app.oauth.dto.GoogleRequest;
import com.yangworld.app.oauth.dto.GoogleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class GoogleServiceImpl implements GoogleService {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String ClIENT_ID;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String SECRET;
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String REDIRECT_URI;
    @Value("${spring.security.oauth2.client.registration.google.scope}")
    private String SCOPE;
    @Value("${spring.security.oauth2.client.provider.google.token-uri}")
    private String TOKEN_URI;


    @Override
    public String getAuthorizeUri() {
        log.info("Client_URI = {}", ClIENT_ID);
        String url = "https://accounts.google.com/o/oauth2/v2/auth?client_id="
                + ClIENT_ID
                + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=code"
                + "&scope=" + SCOPE;
        log.info("url = {}", url);
        return url;
    }

    //https://accounts.google.com/o/oauth2/v2/auth?scope=https%3A//www.googleapis.com/auth/drive.metadata.readonly&access_type=offline&include_granted_scopes=true&response_type=code&state=state_parameter_passthrough_value&redirect_uri=${redirect_uri}&client_id=${client_Id}
    @Override
    public Map<String, Object> getTokens(String code) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(httpHeaders);

        String uri = "https://accounts.google.com/o/oauth2/v2/auth?" +
                "scope=https%3A//www.googleapis.com/auth/drive.metadata.readonly&" +
                "access_type=offline&" +
                "include_granted_scopes=true&" +
                "response_type=code&" +
                "state=state_parameter_passthrough_value&" +
                "redirect_uri=" + "http://localhost:8080/oauth/google/callback" +
                "&client_id=" + "22778471129-fn2c5n58mj16gdr5avfvr8jrn49pquhk.apps.googleusercontent.com";


        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(uri, String.class);

        return null;
    }

    @Override
    public Map<String, Object> getInfo(String accessToken) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(accessToken);

        HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(httpHeaders);
        String uri = "";

        ResponseEntity<Map<String, Object>> responseEntity =
                restTemplate.exchange(URI.create(uri), HttpMethod.GET, httpEntity,
                        new ParameterizedTypeReference<Map<String, Object>>() {
                        });

        return responseEntity.getBody();
    }

    public void getToken(String code) throws IOException {
        // Replace these values with your actual client_id and client_secret
        RestTemplate restTemplate = new RestTemplate();

        String clientId = ClIENT_ID;
        String clientSecret = SECRET;
        String authorizationCode = code;
        String redirectUri = REDIRECT_URI;

        String requestBody = "code=" + URLEncoder.encode(authorizationCode, StandardCharsets.UTF_8)
                + "&client_id=" + URLEncoder.encode(clientId, StandardCharsets.UTF_8)
                + "&client_secret=" + URLEncoder.encode(clientSecret, StandardCharsets.UTF_8)
                + "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8)
                + "&grant_type=authorization_code";

        URL url = new URL("https://oauth2.googleapis.com/token");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);

        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            outputStream.writeBytes(requestBody);
            outputStream.flush();
        }

        int responseCode = connection.getResponseCode();
        InputStream responseStream = (responseCode == HttpURLConnection.HTTP_OK) ?
                connection.getInputStream() : connection.getErrorStream();

        byte[] buffer = new byte[1024];
        int bytesRead;
        StringBuilder response = new StringBuilder();

        while ((bytesRead = responseStream.read(buffer)) != -1) {
            response.append(new String(buffer, 0, bytesRead, StandardCharsets.UTF_8));
        }

        responseStream.close();
        connection.disconnect();

        System.out.println("Response Code: " + responseCode);
        System.out.println("Response Body: " + response.toString());
    }

    public void getGoogleInfo(String authCode) {

        RestTemplate restTemplate = new RestTemplate();

        GoogleRequest googleOAuthRequestParam = GoogleRequest
                .builder()
                .clientId(ClIENT_ID)
                .clientSecret(SECRET)
                .code(authCode)
                .redirectUri(REDIRECT_URI)
                .grantType("authorization_code").build();

        ResponseEntity<GoogleResponse> response = restTemplate.postForEntity("https://oauth2.googleapis.com/token",
                googleOAuthRequestParam, GoogleResponse.class);

        System.out.println("GoogleResponse @ getGoogleInfo : " + response.toString());

        String jwtToken = response.getBody().getId_token();
        Map<String, String> map = new HashMap<>();
        map.put("id_token", jwtToken);

        ResponseEntity<GoogleInfoResponse> infoResponse = restTemplate.postForEntity("https://oauth2.googleapis.com/tokeninfo",
                map, GoogleInfoResponse.class);
        System.out.println("GoogleResponse @ getGoogleInfo : " + response.toString());

        log.info("infoResponse = {}", infoResponse);
//
//        String email = infoResponse.getBody().getEmail();
//        Member member = memberRepository.findByUsername("google" + email);
//        if (member == null) {
//            System.out.println("최초 로그인->자동 회원가입 진행 @ getGoogleInfo.Controller");
//
//            member = Member.builder()
//                    .username("google" + email)
//                    .email(email)
//                    .name(infoResponse.getBody().getName())
//                    .role("ROLE_USER")
//                    .provider("google")
//                    .build();
//            memberRepository.save(member);
////            authenticationSaver(member);
//            return null;
//        }
//        System.out.println("Member @ googleLogin" + member);
//        if (member != null) {
//            authenticationSaver(member);
//        }
    }

}
