package com.ssoystory.dmservice.domain.service;

import com.ssoystory.dmservice.domain.dto.DmMessageOutputDto;
import com.ssoystory.dmservice.domain.dto.MessagesDto;
import com.ssoystory.dmservice.domain.entity.Message;
import com.ssoystory.dmservice.domain.entity.MessagePageReference;
import com.ssoystory.dmservice.domain.entity.MessagePageReferenceKey;
import com.ssoystory.dmservice.common.redis.entity.RedisMessage;
import com.ssoystory.dmservice.domain.repository.DmMessageRepository;
import com.ssoystory.dmservice.domain.repository.MessagePageReferenceRepository;
import com.ssoystory.dmservice.exception.CannotFindUserId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DmServiceImpl implements DmService{
    @Autowired
    private DmMessageRepository dmMessageRepository;
    @Autowired
    private MessagePageReferenceRepository messagePageReferenceRepository;
    private final int PAGESIZE = 100;

    @Override
    @Transactional
    public void save(DmMessageOutputDto dmMessageOutputDto) {
        Message message  = Message.builder()
                .content(dmMessageOutputDto.getContent())
                .receiverId(dmMessageOutputDto.getReceiverId())
                .senderId(dmMessageOutputDto.getSenderId())
                .build();
        dmMessageRepository.save(message);

        Optional<MessagePageReference> existMsgReference = messagePageReferenceRepository
                .findById(dmMessageOutputDto.getSenderId(), dmMessageOutputDto.getReceiverId());

        if (existMsgReference.isEmpty()){
            MessagePageReference messagePageReference = MessagePageReference.builder()
                    .count(Long.valueOf(1))
                    .id(new MessagePageReferenceKey(dmMessageOutputDto.getSenderId(), dmMessageOutputDto.getReceiverId()))
                    .build();
            messagePageReferenceRepository.save(messagePageReference);

        } else {
            MessagePageReference messagePageReference = existMsgReference.get();
            messagePageReference.setCount(existMsgReference.get().getCount()+1);
            messagePageReferenceRepository.save(messagePageReference);
        }
    }

    @Override
    @Transactional
    public void migrateMessages(List<RedisMessage> messageList) {
        List<Message> messages = new ArrayList<>();
        Long senderId=null;
        Long receiverId=null;
        log.info("{}",messageList);
        for (RedisMessage redisMessage: messageList) {
            if(senderId == null || receiverId == null){
                senderId = redisMessage.getSenderId();
                receiverId = redisMessage.getReceiverId();
            }
            Message message = Message.builder()
                    .regDate(redisMessage.getRegDate())
                    .receiverId(redisMessage.getReceiverId())
                    .content(redisMessage.getContent())
                    .senderId(redisMessage.getSenderId())
                    .build();
            messages.add(message);
        }
        dmMessageRepository.saveAll(messages);
        try{
            if(senderId == null || receiverId == null){
                throw new CannotFindUserId("messages doesn't have Ids");
            }
            Optional<MessagePageReference> existingMessageReference = messagePageReferenceRepository.findById(senderId,receiverId);
            MessagePageReference pageReference
                    = existingMessageReference.get();
            pageReference.setCount(existingMessageReference.get().getCount()+1);
            messagePageReferenceRepository.save(pageReference);
        } catch (CannotFindUserId cannotFindUserId){
            log.error("{}",cannotFindUserId.getMessage());
        }
    }

    @Override
    public MessagesDto getPreviousMessage(Long senderId, Long receiverId, Long pageNo) {
        Optional<MessagePageReference> messagePageReference = messagePageReferenceRepository.findById(senderId,receiverId);
        Pageable pageable = PageRequest.of(Math.toIntExact((pageNo)), PAGESIZE);
        List<Message> messages = dmMessageRepository.findMessagesBySenderIdAndReceiverIdOrderByRegDate(senderId,receiverId,pageable);

        return new MessagesDto(messages, messagePageReference.get().getCount());
    }
}
