package com.ssoystory.dmservice.domain.chat;

import com.google.gson.Gson;
import com.ssoystory.dmservice.common.kafka.KafkaConsumerService;
import com.ssoystory.dmservice.common.redis.service.RedisService;
import com.ssoystory.dmservice.domain.dto.*;
import com.ssoystory.dmservice.domain.service.DmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@Component
public class DmWebSocketHandler extends TextWebSocketHandler {
    private final Map<Long, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
    private final DmService dmService;
    private final KafkaConsumerService kafkaConsumerService;
    private final RedisService redisService;

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
        DmMessageInputDto messageDto = gson.fromJson(message.getPayload(), DmMessageInputDto.class);
        IdDto idDto;
        Long receiverId = Long.valueOf(-1);
        DmMessageOutputDto dmMessageOutputDto = new DmMessageOutputDto();
        try{
            String _idDto=kafkaConsumerService.receiveUserId();
            idDto = gson.fromJson(_idDto, IdDto.class);
            receiverId = idDto.getUserId();
            log.info("Kafka receive Message = {}",idDto);

            dmMessageOutputDto.setContent(messageDto.getContent());
            dmMessageOutputDto.setSenderId(messageDto.getSenderId());
            dmMessageOutputDto.setReceiverId(idDto.getUserId());
        } catch (Exception e) {
            log.info("{}",e);
        }

        WebSocketSession receiverSession = sessionMap.get(receiverId);

        if(receiverId != -1 && receiverSession.isOpen()){
            try {
                receiverSession.sendMessage(message);
                redisService.save(dmMessageOutputDto);
            } catch (Exception e) {
                log.error("message handling error = {}", e.getMessage());
            }
        } else if ( receiverId != -1 && !receiverSession.isOpen()) {
            dmService.save(dmMessageOutputDto);
        } else {

        }
    }

    private void handleEntry(WebSocketSession session, TextMessage message) {
        EnterNotificationDto enterNotificationDto = gson.fromJson(message.getPayload(), EnterNotificationDto.class);
        sessionMap.put(enterNotificationDto.getId(), session);
        List<DmMessageOutputDto> dmMessageInputDtos = dmService.getPreviousMessages(enterNotificationDto.getId());
        Map<Long, String> idToNickname = new HashMap<>();
        for (DmMessageOutputDto dmMessageInputDto : dmMessageInputDtos) {

        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}
