package com.ssoystory.gateway.filters;

import com.ssoystory.gateway.jwt.JwtUtils;
import com.ssoystory.gateway.jwt.TokenClaims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationGlobalFilter implements GlobalFilter, Ordered {
    private static final String VERIFICATION_TARGET_PATH = "/api/jwt";
    private final JwtUtils jwtUtils;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        if (!matchesPath(request)) {
            return chain.filter(exchange);
        }
        if (!containsAuthorization(request)) {
            return onError(response, "Authorization header is missing in request",HttpStatus.UNAUTHORIZED);
        }
        String token = extractToken(request);
        if (!jwtUtils.isAccessTokenValid(token)) {
            return onError(response, "Authorization header is not valid",HttpStatus.FORBIDDEN);
        }

        addAuthorizationHeaders(request, token);

        return chain.filter(exchange);
    }

    private boolean matchesPath(ServerHttpRequest request) {
        return request.getURI().getPath().startsWith(VERIFICATION_TARGET_PATH);
    }
    private boolean containsAuthorization(ServerHttpRequest request) {
        return request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }
    private Mono<Void> onError(ServerHttpResponse response, String message, HttpStatus status) {
        response.setStatusCode(status);
        DataBuffer buffer = response.bufferFactory().wrap(message.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
    private String extractToken(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).get(0);
    }
    private void addAuthorizationHeaders(ServerHttpRequest request, String token) {
        TokenClaims tokenUser = jwtUtils.acc_decode(token);

        request.mutate()
                .header("X-Authorization-Id", tokenUser.getId().toString())
                .header("X-Authorization-Authorities", tokenUser.getAuthorities())
                .build();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
