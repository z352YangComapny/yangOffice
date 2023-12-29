package com.ssoystory.dmservice.common.redis.service;

import com.ssoystory.dmservice.domain.dto.DmMessageOutputDto;
import com.ssoystory.dmservice.common.redis.entity.RedisMessage;
import com.ssoystory.dmservice.domain.service.DmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@Service
public class RedisServiceImpl implements RedisService{
    @Autowired
    private RedisTemplate<String, RedisMessage> redisTemplate;
    @Autowired
    private DmService dmService;
    private final String KEY_PREFIX = "message_";

    @Override
    public void save(DmMessageOutputDto dmMessageOutputDto) {
        // 현재 시간 가져오기
        Instant now = Instant.now();
        ZoneId zoneId = ZoneId.of("Asia/Seoul");
        Timestamp regDate = Timestamp.from(now.atZone(zoneId).toInstant());

        RedisMessage message = RedisMessage.builder()
                .senderId(dmMessageOutputDto.getSenderId())
                .receiverId(dmMessageOutputDto.getReceiverId())
                .content(dmMessageOutputDto.getContent())
                .regDate(regDate)
                .build();

        redisTemplate.opsForList().rightPush(KEY_PREFIX+dmMessageOutputDto.getSenderId(), message);
    }

    @Override
    public void migrateMessages(Long id) {
        Long size = redisTemplate.opsForList().size(KEY_PREFIX+id);
        List<RedisMessage> messageList = redisTemplate.opsForList().range(KEY_PREFIX+id,0,size-1);
        if (size > 0) {
            redisTemplate.delete(KEY_PREFIX+id);
        }
        dmService.migrateMessages(messageList);
    }


}