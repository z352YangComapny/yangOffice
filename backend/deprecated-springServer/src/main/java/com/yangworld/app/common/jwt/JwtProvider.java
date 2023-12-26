package com.yangworld.app.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.member.dto.SignUpDto;
import com.yangworld.app.domain.member.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@Slf4j
public class JwtProvider {
    @Bean
    public String createAccessToken(PrincipalDetails principalDetails) {
        log.info("{}",principalDetails);
        String accessToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", principalDetails.getId())
                .withClaim("username", principalDetails.getUsername())
                .withClaim("nickname", principalDetails.getNickname())
                .withClaim("authorities", principalDetails.getAuthorities().toString())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        return accessToken;
    }
    @Bean
    public String createRefreshToken(PrincipalDetails principalDetails) {
        String refreshToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.REF_EXPIRATION_TIME))
                .withClaim("id", principalDetails.getId())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        return refreshToken;
    }

    public String createAccessToken(Member member) {
        String accessToken = JWT.create()
                .withSubject(member.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", member.getId())
                .withClaim("username", member.getUsername())
                .withClaim("nickname", member.getNickname())
                .withClaim("authorities", "[ROLE_USER]")
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        return accessToken;
    }

    public String createRefreshToken(Member member) {
        String refreshToken = JWT.create()
                .withSubject(member.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.REF_EXPIRATION_TIME))
                .withClaim("id", member.getId())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        return refreshToken;
    }

    public String createAccessToken(SignUpDto signUpDto) {
        log.info("{}",signUpDto);
        String accessToken = JWT.create()
                .withSubject(signUpDto.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", signUpDto.getId())
                .withClaim("username", signUpDto.getUsername())
                .withClaim("nickname", signUpDto.getNickname())
                .withClaim("authorities", "[ROLE_USER]")
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        return accessToken;
    }

    public String createRefreshToken(SignUpDto signUpDto) {
        String refreshToken = JWT.create()
                .withSubject(signUpDto.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.REF_EXPIRATION_TIME))
                .withClaim("id", signUpDto.getId())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        return refreshToken;
    }
}
