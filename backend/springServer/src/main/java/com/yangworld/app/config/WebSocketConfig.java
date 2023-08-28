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
			.withSockJS(); // 웹소켓 미지원 브라우져를 위한 설정
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 1. SimpleBroker로 처리하는 url 등록
		// 해당하는 경로를 subscribe하는 client에게 메세지를 전달하는 작업 수행
		registry.enableSimpleBroker("/storyMain", "/dm", "/chat");
		
		// 2. MessageHandler로 처리하는 url 등록
		// Client에서 Send 요청을 처리
		registry.setApplicationDestinationPrefixes("/app");

	}
}
