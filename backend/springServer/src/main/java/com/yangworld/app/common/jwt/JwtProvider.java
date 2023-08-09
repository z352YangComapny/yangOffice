package com.yangworld.app.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.yangworld.app.config.auth.PrincipalDetails;
import org.springframework.context.annotation.Bean;

import java.util.Date;

public class JwtProvider {
    @Bean
    public String createAccessToken(PrincipalDetails principalDetails) {
        String accessToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", principalDetails.getMember().getId())
                .withClaim("username", principalDetails.getMember().getUsername())
                .withClaim("authority", principalDetails.getMember().getAuth().name())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        return accessToken;
    }
    @Bean
    public String createRefreshToken(PrincipalDetails principalDetails) {
        String refreshToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.REF_EXPIRATION_TIME))
                .withClaim("id", principalDetails.getMember().getId())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        return refreshToken;
    }
}
