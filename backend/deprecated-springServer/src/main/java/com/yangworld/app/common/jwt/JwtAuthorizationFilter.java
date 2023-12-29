package com.yangworld.app.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.yangworld.app.common.redis.entity.RefreshToken;
import com.yangworld.app.common.redis.service.RedisService;
import com.yangworld.app.common.redis.service.RefreshTokenService;
import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.member.dto.LoginDto;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private MemberRepository memberRepository;
    private RedisService redisService;
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository , RedisService redisService) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
        this.redisService = redisService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtHeader = request.getHeader("Authorization");
        log.info("jwtHeader={}",jwtHeader);
        if( jwtHeader == null || !jwtHeader.startsWith("Bearer ")){
            log.warn("접속 토큰을 찾지 못했습니다.");
            chain.doFilter(request,response);
            return;
        }
        String jwtToken = request.getHeader("Authorization").replace("Bearer ","");
        try{
            Algorithm algorithm = Algorithm.HMAC512(JwtProperties.SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(jwtToken);
            Claim usernameClaim = decodedJWT.getClaim("username");
            String username = usernameClaim.asString();
            Claim idClaim = decodedJWT.getClaim("id");
            Claim authoritiesClaim = decodedJWT.getClaim("authorities");
            log.info("Claims={},{},{}",idClaim,usernameClaim,authoritiesClaim);
            PrincipalDetails principalDetails = memberRepository.loadUserByUsername(username);
            log.info("member={}",principalDetails);
            Object _refreshToken = redisService.getData(String.valueOf(idClaim));
            if(_refreshToken == null){
                chain.doFilter(request, response);
                return;
            }
            Gson gson = new Gson();
            RefreshToken refreshToken = gson.fromJson(_refreshToken.toString(), RefreshToken.class);
            log.info("refreshToken={}",refreshToken);

            if (decodedJWT.getExpiresAt().before(new Date())) {
                logger.warn("접속 토큰이 만료되었습니다.");
                if(verifier.verify(refreshToken.getTokenVal()).getExpiresAt().before(new Date())){
                    JwtProvider jwtProvider = new JwtProvider();
                    String newAccessToken =jwtProvider.createAccessToken(principalDetails);
                    logger.info("refreshToken 남아있음 : new AccessToken");
                    response.addHeader(JwtProperties.ACC_HEADER_STRING, JwtProperties.TOKEN_PREFIX+newAccessToken);
                }else {
                    chain.doFilter(request, response);
                    return;
                }
            }
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
            log.info("principalDetails.getAuthorities()={}",principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
            log.warn("유효하지 않은 토큰");
        }
        chain.doFilter(request,response);
    }
}
