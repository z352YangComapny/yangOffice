package com.ssoystory.dmservice.common.redis.service;

import com.ssoystory.dmservice.domain.dto.DmMessageOutputDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService{
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public void save(DmMessageOutputDto dmMessageOutputDto) {

    }
}
