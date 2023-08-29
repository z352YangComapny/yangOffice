package com.yangworld.app.oauth.google.service;

import java.util.Map;

public interface GoogleService {


    String getAuthorizeUri();

    Map<String, Object> getGoogleInfo(String accessToken);

    Map<String, Object> getTokens(String code);
}
