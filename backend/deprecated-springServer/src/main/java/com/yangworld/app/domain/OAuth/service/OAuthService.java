package com.yangworld.app.domain.OAuth.service;

import com.yangworld.app.domain.OAuth.dto.NaverDto;
import com.yangworld.app.domain.OAuth.dto.OAuthDto;


public interface OAuthService {
    String[] kakao(OAuthDto oAuthDto);

    String[] naver(OAuthDto oAuthDto);
}
