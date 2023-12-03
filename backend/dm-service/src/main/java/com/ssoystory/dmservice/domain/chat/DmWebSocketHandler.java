package com.ssoystory.dmservice.domain.chat;

import com.google.gson.Gson;
import com.ssoystory.dmservice.domain.dto.DmMessageDto;
import com.ssoystory.dmservice.domain.dto.EnterNotificationDto;
import com.ssoystory.dmservice.domain.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@Component
public class DmWebSocketHandler extends TextWebSocketHandler {
    private final Map<Long, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
    Gson gson = new Gson();
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        MessageDto messageDto = gson.fromJson(message.getPayload(), MessageDto.class);

        switch (messageDto.getMsgType()){
            case 0:
                handleEntry(session, message);
                break;
            case 1:
                handleDM(session, message);
                break;
            default:
                break;
        }
    }

    private void handleDM(WebSocketSession session, TextMessage message) {
        DmMessageDto messageDto = gson.fromJson(message.getPayload(), DmMessageDto.class);


    }

    private void handleEntry(WebSocketSession session, TextMessage message) {
        EnterNotificationDto enterNotificationDto = gson.fromJson(message.getPayload(), EnterNotificationDto.class);
        sessionMap.put(enterNotificationDto.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}
