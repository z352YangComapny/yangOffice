package com.ssoystory.memberservice.common.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenClaims {
    private Long Id;
    private String username;
    private String authorities;
    private String nickname;
}