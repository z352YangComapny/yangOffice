package com.yangworld.app.domain.OAuth.provider.kakao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoOAuth {
    private String token_type;
    private String access_token;
    private String id_token;
    private Integer expires_in;
    private String refresh_token;
    private Integer refresh_token_expires_in;
    private String scope;
}
