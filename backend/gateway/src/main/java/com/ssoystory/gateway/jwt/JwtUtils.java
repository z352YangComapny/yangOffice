package com.ssoystory.gateway.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
@PropertySource("classpath:JwtConfig.properties")
public class JwtUtils implements InitializingBean {

    private final String SECRET;
    private final String PREFIX;
    private final String ACCESS_TOKEN_KEY;
    private final Long ACCESS_TOKEN_EXP;
    private final String REFRESH_TOKEN_KEY;
    private final Long REFRESH_TOKEN_EXP;


    private Algorithm algorithm;
    private JWTVerifier jwtVerifier;

    public JwtUtils(@Value("${jwt.properties.secret}") String secret,
                    @Value("${jwt.properties.token.prefix}") String prefix,
                    @Value("${jwt.properties.access-token.header.key}") String accessTokenKey,
                    @Value("${jwt.properties.access-token.expiration.time}") String accessTokenExp,
                    @Value("${jwt.properties.refresh-token.header.key}") String refreshTokenKey,
                    @Value("${jwt.properties.refresh-token.expiration.time}")String refreshTokenExp
                    ) {
        this.SECRET = secret;
        this.PREFIX = prefix;
        this.ACCESS_TOKEN_KEY = accessTokenKey;
        this.ACCESS_TOKEN_EXP = Long.valueOf(accessTokenExp);
        this.REFRESH_TOKEN_KEY = refreshTokenKey;
        this.REFRESH_TOKEN_EXP = Long.valueOf(refreshTokenExp);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.algorithm = Algorithm.HMAC512(SECRET);
        this.jwtVerifier = JWT.require(algorithm).acceptLeeway(5).build();
    }

    public boolean isValid(String tokenValue) {
        try {
            jwtVerifier.verify(tokenValue);
            return true;
        } catch (RuntimeException e) {
            log.warn("isValidError={}", e);
            return false;
        }
    }

    public TokenClaims acc_decode(String token) {
        DecodedJWT jwt = JWT.decode(token);

        Long Id = Long.parseLong(jwt.getSubject());
        String authorities = jwt.getClaim("authorities").asString();
        String nickname = jwt.getClaim("nickname").asString();
        String username = jwt.getClaim("username").asString();

        TokenClaims accTokenClaims = TokenClaims.builder()
                .Id(Id)
                .username(username)
                .nickname(nickname)
                .authorities(authorities)
                .build();

        return accTokenClaims;
    }

    public TokenClaims ref_decode(String token) {
        jwtVerifier.verify(token);

        DecodedJWT jwt = JWT.decode(token);

        Long Id = Long.parseLong(jwt.getSubject());


        TokenClaims refTokenClaims = TokenClaims.builder()
                .Id(Id)
                .build();

        return refTokenClaims;
    }

    String accessTokenProvider(TokenClaims tokenClaims) {
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + ACCESS_TOKEN_EXP);

        return JWT.create()
                .withSubject(tokenClaims.getId().toString())
                .withClaim("username", tokenClaims.getUsername())
                .withClaim("nickname", tokenClaims.getNickname())
                .withClaim("authorities", tokenClaims.getAuthorities())
                .withExpiresAt(expiresAt)
                .withIssuedAt(now)
                .sign(algorithm);
    }

    String refreshTokenProvider(TokenClaims tokenClaims) {
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + REFRESH_TOKEN_EXP);

        return JWT.create()
                .withSubject(tokenClaims.getId().toString())
                .withExpiresAt(expiresAt)
                .withIssuedAt(now)
                .sign(algorithm);
    }
}

