package com.yangworld.app.oauth.google.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoogleRequest {


    private String clientId;
    private String clientSecret;
    private String code;
    private String redirectUri;
    private String grantType;
}
