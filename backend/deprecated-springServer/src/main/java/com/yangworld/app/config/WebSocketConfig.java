package com.yangworld.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/stomp")
			.setAllowedOrigins("http://localhost:3000", "http://localhost:7070", "http://localhost:8080")
			.withSockJS();
	}
	
	
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 1. SimpleBroker로 처리하는 url 등록
		registry.enableSimpleBroker("/storyMain");
		
		// 2. MessageHandler로 처리하는 url 등록
		registry.setApplicationDestinationPrefixes("/app");
	}
	
	
}
