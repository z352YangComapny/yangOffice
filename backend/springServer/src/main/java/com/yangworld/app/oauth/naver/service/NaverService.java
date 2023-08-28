package com.yangworld.app.oauth.naver.service;

import java.util.Map;

public interface NaverService {

    String getAuthorizeUri();

    Map<String, Object> getTokens(String code);

    Map<String, Object> getProfile(String accessToken);
}
