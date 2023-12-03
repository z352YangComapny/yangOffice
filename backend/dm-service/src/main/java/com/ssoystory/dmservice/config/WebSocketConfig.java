package com.ssoystory.dmservice.config;

import com.ssoystory.dmservice.domain.chat.DmWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(dmSignalingSocketHandler() , "/api/dm")
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler dmSignalingSocketHandler() {
        return new DmWebSocketHandler();
    }
}
