package com.yangworld.app.oauth.kakao.service;

import java.io.IOException;
import java.util.Map;

public interface GoogleService {


    String getAuthorizeUri();

    Map<String, Object> getTokens(String code);

    Map<String, Object> getInfo(String accessToken);

    void getToken(String code) throws IOException;

    void getGoogleInfo(String code);
}
