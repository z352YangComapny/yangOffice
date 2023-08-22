package com.yangworld.app.oauth.service;

import java.util.Map;

import org.springframework.stereotype.Service;


public interface KakaoService {

	String getAuthorizeUri();

	Map<String, Object> getTokens(String code);

	Map<String, Object> getProfile(String accessToken);
}
