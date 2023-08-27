package com.yangworld.app.oauth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GoogleServiceImpl implements GoogleService {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String Client_URI;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String SECRET;
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String REDIRECT_URI;


    @Override
    public String getAuthorizeUri() {
        log.info("Client_URI = {}", Client_URI);
        String url = "https://accounts.google.com/o/oauth2/v2/auth?client_id="
                + Client_URI
                + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=code"
                + "scope=email";
        log.info("url = {}", url);
        return url;
    }
}
