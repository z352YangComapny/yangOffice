package com.yangworld.app.config;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/stomp").setAllowedOrigins("*")
			.withSockJS(); // 웹소켓 미지원 브라우져를 위한 설정
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 1. SimpleBroker로 처리하는 url 등록
		registry.enableSimpleBroker("/story");
		
		// 2. MessageHandler로 처리하는 url 등록
		registry.setApplicationDestinationPrefixes("/story");

	}
}
