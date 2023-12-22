package com.ssoystory.dmservice.domain.service;

import com.ssoystory.dmservice.domain.dto.DmMessageOutputDto;
import com.ssoystory.dmservice.domain.entity.Message;
import com.ssoystory.dmservice.domain.entity.MessagePageReference;
import com.ssoystory.dmservice.domain.entity.MessagePageReferenceKey;
import com.ssoystory.dmservice.domain.entity.RedisMessage;
import com.ssoystory.dmservice.domain.repository.DmMessageRepository;
import com.ssoystory.dmservice.domain.repository.MessagePageReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DmServiceImpl implements DmService{
    @Autowired
    private DmMessageRepository dmMessageRepository;
    @Autowired
    private MessagePageReferenceRepository messagePageReferenceRepository;

    @Override
    @Transactional
    public void save(DmMessageOutputDto dmMessageOutputDto) {
        Message message  = Message.builder()
                .content(dmMessageOutputDto.getContent())
                .receiverId(dmMessageOutputDto.getReceiverId())
                .senderId(dmMessageOutputDto.getSenderId())
                .build();
        dmMessageRepository.save(message);

        MessagePageReferenceKey messagePageReferenceKey = MessagePageReferenceKey
                .builder()
                .receiverId(dmMessageOutputDto.getReceiverId())
                .senderId(dmMessageOutputDto.getSenderId())
                .build();

        Optional<MessagePageReference> existMsgReference = messagePageReferenceRepository.findById(messagePageReferenceKey);

        if (existMsgReference.isEmpty()){
            MessagePageReference messagePageReference = MessagePageReference.builder()
                    .count(Long.valueOf(1))
                    .id(messagePageReferenceKey)
                    .build();
            messagePageReferenceRepository.save(messagePageReference);

        } else {
            MessagePageReference messagePageReference = existMsgReference.get();
            messagePageReference.setCount(existMsgReference.get().getCount()+1);
            messagePageReferenceRepository.save(messagePageReference);
        }
    }

    @Override
    public void migrateMessages(List<Object> messageList) {
        List<Message> messages = new ArrayList<>();
        for (Object redisMessage: messageList) {
            RedisMessage _redisMessage = ((RedisMessage)redisMessage);

            Message message = Message.builder()
                    .regDate(_redisMessage.getRegDate())
                    .receiverId(_redisMessage.getReceiverId())
                    .content(_redisMessage.getContent())
                    .senderId(_redisMessage.getSenderId())
                    .build();
            messages.add(message);
        }
        dmMessageRepository.saveAll(messages);
    }

    @Override
    public void getPreviousMessage(Long senderId, Long userId, Long pageNo) {

    }
}
