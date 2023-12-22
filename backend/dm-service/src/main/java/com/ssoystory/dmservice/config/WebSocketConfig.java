package com.ssoystory.dmservice.config;

import com.ssoystory.dmservice.common.kafka.KafkaConsumerService;
import com.ssoystory.dmservice.common.kafka.KafkaProducerService;
import com.ssoystory.dmservice.common.redis.service.RedisService;
import com.ssoystory.dmservice.domain.chat.DmWebSocketHandler;
import com.ssoystory.dmservice.domain.service.DmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private final DmService dmService;
    @Autowired
    private final KafkaConsumerService kafkaConsumerService;
    @Autowired
    private final KafkaProducerService kafkaProducerService;
    @Autowired
    private final RedisService redisService;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(dmSignalingSocketHandler() , "/api/dm")
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler dmSignalingSocketHandler() {
        return new DmWebSocketHandler(dmService , kafkaConsumerService, kafkaProducerService,redisService);
    }
}
