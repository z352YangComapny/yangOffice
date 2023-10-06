package com.ssoystory.gateway.filters;

import com.ssoystory.gateway.jwt.JwtUtils;
import com.ssoystory.gateway.jwt.TokenClaims;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
public class JwtAuthenticationGatewayFilter extends AbstractGatewayFilterFactory<JwtAuthenticationGatewayFilter.Config> {
    private final JwtUtils jwtUtils;

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("authorities");
    }
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            if (!containsAuthorization(request)) {
                return onError(response, "missing authorization header", HttpStatus.BAD_REQUEST);
            }

            String token = extractToken(request);
            if (!jwtUtils.isValid(token)) {
                return onError(response, "invalid authorization header", HttpStatus.BAD_REQUEST);
            }

            TokenClaims tokenClaims = jwtUtils.acc_decode(token);
            if (!hasRole(tokenClaims, config.authorities)) {
                return onError(response, "invalid authorities", HttpStatus.FORBIDDEN);
            }

            addAuthorizationHeaders(request, tokenClaims);
            return chain.filter(exchange);
        };
    }
    private boolean containsAuthorization(ServerHttpRequest request) {
        return request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }

    private String extractToken(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).get(0);
    }

    private boolean hasRole(TokenClaims tokenClaims, String role) {
        return role.equals(tokenClaims.getAuthorities());
    }

    private void addAuthorizationHeaders(ServerHttpRequest request, TokenClaims tokenClaims) {
        request.mutate()
                .header("X-Authorization-Id", tokenClaims.getId().toString())
                .header("X-Authorization-Authorities", tokenClaims.getAuthorities())
                .build();
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