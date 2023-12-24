package com.ssoystory.dmservice.domain.chat;

import com.google.gson.Gson;
import com.ssoystory.dmservice.common.kafka.dto.IdDto;
import com.ssoystory.dmservice.common.kafka.service.KafkaConsumerService;
import com.ssoystory.dmservice.common.kafka.service.KafkaProducerService;
import com.ssoystory.dmservice.common.redis.service.RedisService;
import com.ssoystory.dmservice.domain.dto.*;
import com.ssoystory.dmservice.domain.service.DmService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class DmWebSocketHandler extends TextWebSocketHandler {
    private final Map<Long, SessionAndQueue> sessionMap = new ConcurrentHashMap<>();
    private final Map<WebSocketSession, Long> reverseLookupMap = new ConcurrentHashMap<>();
    @Autowired
    private DmService dmService;
    @Autowired
    private KafkaConsumerService kafkaConsumerService;
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private RedisService redisService;


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
            case 2:
                getPreviousMessage(session, message);
                break;
            default:
                break;
        }
    }

    private void getPreviousMessage(WebSocketSession session, TextMessage message) {
        DmRoomInfoDto dmRoomInfoDto = gson.fromJson(message.getPayload(), DmRoomInfoDto.class);
        kafkaProducerService.sendNickname(dmRoomInfoDto.getReceiverNickname());
        IdDto idDto=null;
        try{
            String _idDto = kafkaConsumerService.receiveUserId();
            idDto = gson.fromJson(_idDto, IdDto.class);
            log.debug("Kafka receive Message = {}",idDto);
            if(idDto.getUserId() == null) {
                log.error("cannot get UserId");
                return;
            };
        } catch (InterruptedException interruptedException){
            log.error("{}",interruptedException.getMessage());
        }
        dmService.getPreviousMessage(dmRoomInfoDto.getSenderId(), Objects.requireNonNull(idDto).getUserId(),dmRoomInfoDto.getPageNo());
    }

    private void handleDM(WebSocketSession session, TextMessage message) throws IOException {
        DmMessageInputDto messageDto = gson.fromJson(message.getPayload(), DmMessageInputDto.class);
        kafkaProducerService.sendNickname(messageDto.getNickname());
        sessionMap.get(messageDto.getSenderId()).getMessageInputDtoQueue().offer(message);
        if(sessionMap.get(messageDto.getSenderId()).getMessageInputDtoQueue().size()>10){
            sessionMap.get(messageDto.getSenderId()).getWebSocketSession().sendMessage(new TextMessage("Sever is Busy, Try Later"));
        }
        handleDmQueue(messageDto.getSenderId());
    }

    private void handleDmQueue(Long id) {
        IdDto idDto;
        Long receiverId = Long.valueOf(-1);
        DmMessageOutputDto dmMessageOutputDto = new DmMessageOutputDto();
        while(!sessionMap.get(id).getMessageInputDtoQueue().isEmpty()) {
            TextMessage _message = sessionMap.get(id).getMessageInputDtoQueue().poll();
            DmMessageInputDto messageDto = gson.fromJson(_message.getPayload(), DmMessageInputDto.class);
            try {
                String _idDto = kafkaConsumerService.receiveUserId();
                idDto = gson.fromJson(_idDto, IdDto.class);
                receiverId = idDto.getUserId();
                log.info("Kafka receive Message = {}", idDto);

                dmMessageOutputDto.setContent(messageDto.getContent());
                dmMessageOutputDto.setSenderId(messageDto.getSenderId());
                dmMessageOutputDto.setReceiverId(idDto.getUserId());
            } catch (Exception e) {
                log.info("{}", e);
            }

            WebSocketSession receiverSession = sessionMap.get(receiverId).getWebSocketSession();
            sessionMap.get(receiverId).getMessageInputDtoQueue().offer(_message);

            if (receiverId != -1 && receiverSession.isOpen()) {
                try {
                    receiverSession.sendMessage(_message);
                    redisService.save(dmMessageOutputDto);
                } catch (Exception e) {
                    log.error("message handling error = {}", e.getMessage());
                }
            } else if (receiverId != -1 && !receiverSession.isOpen()) {
                dmService.save(dmMessageOutputDto);
            }
        }
    }

    private void handleEntry(WebSocketSession session, TextMessage message) {
        EnterNotificationDto enterNotificationDto = gson.fromJson(message.getPayload(), EnterNotificationDto.class);
        sessionMap.put(enterNotificationDto.getId(), new SessionAndQueue(session,new LinkedList<>()));
        reverseLookupMap.put(session,enterNotificationDto.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        session.sendMessage(new TextMessage("Session Closed!"));
        Long id = reverseLookupMap.get(session);

        if (!sessionMap.get(id).getMessageInputDtoQueue().isEmpty()) {
            handleDmQueue(id);
        } else {
            reverseLookupMap.remove(session);
            sessionMap.remove(id);
        }
        redisService.migrateMessages(id);
        super.afterConnectionClosed(session, status);
    }
}

@Data
@AllArgsConstructor
class SessionAndQueue {
    private WebSocketSession webSocketSession;
    private Queue<TextMessage> messageInputDtoQueue;
}