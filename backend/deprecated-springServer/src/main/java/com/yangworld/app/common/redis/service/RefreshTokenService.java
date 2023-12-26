package com.yangworld.app.common.redis.service;

import com.yangworld.app.common.jwt.JwtProperties;
import com.yangworld.app.common.redis.entity.RefreshToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service("refreshTokenService")
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenService implements RedisService{
    @Autowired
    private final RedisTemplate<String, Object> redisTemplate;
    private static String KEY_PREFIX = "RefreshToken:";

    @Override
    public void setData(Object object) {
        RefreshToken token = (RefreshToken) object;
        Object isAdded = getData(String.valueOf(token.getUserId()));
        if(isAdded == null){
            redisTemplate.opsForValue().set(KEY_PREFIX+token.getUserId(), token, Duration.ofSeconds(JwtProperties.REF_EXPIRATION_TIME/1000));
        }
    }

    @Override
    public Object getData(String key) {
        return redisTemplate.opsForValue().get(KEY_PREFIX + key);
    }

    @Override
    public void deleteData(String key) {
        redisTemplate.delete(KEY_PREFIX + key);
    }
}
