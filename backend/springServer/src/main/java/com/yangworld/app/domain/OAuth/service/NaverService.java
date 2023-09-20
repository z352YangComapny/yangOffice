package com.yangworld.app.domain.OAuth.service;

import com.yangworld.app.domain.OAuth.dto.NaverDto;

import java.util.Map;

public interface NaverService {

//    String getAuthorizeUri();

    Map<String, Object> getTokens(String code);

    Map<String, Object> getProfile(String accessToken);

    String[] naver(NaverDto naverDto);
}