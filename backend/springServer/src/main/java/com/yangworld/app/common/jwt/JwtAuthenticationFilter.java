package com.yangworld.app.common.jwt;

import com.google.gson.Gson;
import com.yangworld.app.common.redis.entity.RefreshToken;
import com.yangworld.app.common.redis.service.RedisService;
import com.yangworld.app.common.redis.service.RefreshTokenService;
import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.member.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final RedisService redisService;
    private final JwtProvider jwtProvider = new JwtProvider();


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("로그인 시도중...");
        Gson gson = new Gson();
        LoginDto loginDto = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            loginDto = gson.fromJson(reader, LoginDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("loginDto = {} ",loginDto);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        
        log.info("authenticationToken = {} ",authenticationToken);

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        log.info("authentication = {} ",authentication);
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("successfulAuthentication autResult= {}",authResult);
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        String accessToken = jwtProvider.createAccessToken(principalDetails);
        String refreshToken = jwtProvider.createRefreshToken(principalDetails);

        RefreshToken refreshTokenObj = RefreshToken.builder()
                .userId(principalDetails.getMember().getId())
                .tokenVal(refreshToken).build();

        redisService.setData(refreshTokenObj);
        response.addHeader(JwtProperties.ACC_HEADER_STRING,JwtProperties.TOKEN_PREFIX+accessToken);
    }
}
