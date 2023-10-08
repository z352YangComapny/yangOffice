package com.ssoystory.memberservice.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;

@PropertySource("classpath:JwtConfig.properties")
@Component
public class JwtProvider {
    @Value("${jwt.properties.access-token.expiration.time}") private int ACCESS_TOKEN_EXP;
    @Value("${jwt.properties.refresh-token.expiration.time}") private int REFRESH_TOKEN_EXP;
    @Value("${jwt.properties.secret}") private String SECRET;

    public String accessTokenProvider(TokenClaims tokenClaims) {
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + ACCESS_TOKEN_EXP);

        return JWT.create()
                .withSubject(tokenClaims.getId().toString())
                .withClaim("username", tokenClaims.getUsername())
                .withClaim("nickname", tokenClaims.getNickname())
                .withClaim("authorities", tokenClaims.getAuthorities())
                .withExpiresAt(expiresAt)
                .withIssuedAt(now)
                .sign(Algorithm.HMAC512(SECRET));
    }

    public String refreshTokenProvider(TokenClaims tokenClaims) {
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + REFRESH_TOKEN_EXP);

        return JWT.create()
                .withSubject(tokenClaims.getId().toString())
                .withExpiresAt(expiresAt)
                .withIssuedAt(now)
                .sign(Algorithm.HMAC512(SECRET));
    }

}
