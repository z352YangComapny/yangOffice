package com.ssoystory.gateway.filters;

import com.ssoystory.gateway.jwt.JwtUtils;
import com.ssoystory.gateway.jwt.RefreshAccessTokenDTO;
import com.ssoystory.gateway.jwt.TokenClaims;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class JwtAuthorizationGatewayFilter extends AbstractGatewayFilterFactory<JwtAuthorizationGatewayFilter.Config> {
    private final JwtUtils jwtUtils;

    public JwtAuthorizationGatewayFilter(@Autowired JwtUtils jwtUtils) {
        super(JwtAuthorizationGatewayFilter.Config.class);
        this.jwtUtils = jwtUtils;
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("authorities");
    }
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("JwtAuthorizationGatewayFilter Activated");

            if (!containsAuthorization(request)) {
                return onError(response, "missing authorization header", HttpStatus.BAD_REQUEST);
            }

            String accessToken = extractToken(request).startsWith("Bearer ") ? extractToken(request).substring(7) : extractToken(request);
            log.info("acc Token = {}", accessToken);
            RefreshAccessTokenDTO refreshAccessTokenDTO = jwtUtils.isAccessTokenValid(accessToken);

            switch (refreshAccessTokenDTO.getStatus()){
                case 0:
                    log.info("Access Token is Valid");
                    break;
                case 1:
                    log.info("Access Token is Expired, New Token is created");
                    refreshAccessToken(request,refreshAccessTokenDTO.getNewAccessTokenOrElseErrorMessage());
                    break;
                case 2:
                    log.info(refreshAccessTokenDTO.getNewAccessTokenOrElseErrorMessage());
                    return onError(response, "invalid authorization header", HttpStatus.BAD_REQUEST);
                default:
                    log.info(refreshAccessTokenDTO.getNewAccessTokenOrElseErrorMessage());
                    return onError(response, "Error. Please contact the administrator", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            TokenClaims tokenClaims = jwtUtils.acc_decode(accessToken);
            log.info("acctoken_Claims={}",tokenClaims);
            if (!hasRole(tokenClaims, config.authorities)) {
                return onError(response, "invalid authorities", HttpStatus.FORBIDDEN);
            }
            addAuthorizationHeaders(request,tokenClaims);
            return chain.filter(exchange);
        };
    }

    private void refreshAccessToken(ServerHttpRequest request,String newAccessTokenOrElseErrorMessage) {
        request.mutate().header(HttpHeaders.AUTHORIZATION,newAccessTokenOrElseErrorMessage).build();
    }

    private boolean containsAuthorization(ServerHttpRequest request) {
        return request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }

    private String extractToken(ServerHttpRequest request) {
            return request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).get(0);
    }

    private boolean hasRole(TokenClaims tokenClaims, String authorities) {
        log.info("authorities = {}",authorities);
        return authorities.equals(tokenClaims.getAuthorities());
    }

    private void addAuthorizationHeaders(ServerHttpRequest request, TokenClaims tokenClaims) {
        request.mutate()
                .header("X-Authorization-Id", tokenClaims.getId().toString())
                .header("X-Authorization-Username", tokenClaims.getUsername())
                .header("X-Authorization-Nickname", tokenClaims.getNickname())
                .header("X-Authorization-Authorities", tokenClaims.getAuthorities())
                .build();
        log.info("X-Authorization-Id = {}", tokenClaims.getId().toString());
        log.info("X-Authorization-Username = {}", tokenClaims.getUsername());
        log.info("X-Authorization-Nickname = {}", tokenClaims.getNickname());
        log.info("X-Authorization-Authorities = {}", tokenClaims.getAuthorities());
    }
    private Mono<Void> onError(ServerHttpResponse response, String message, HttpStatus status) {
        response.setStatusCode(status);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
        DataBuffer buffer = response.bufferFactory().wrap(message.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

    @Data
    public static class Config {
        private String authorities;
    }
}
