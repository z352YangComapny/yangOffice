package com.ssoystory.gateway.redis.service;

import com.ssoystory.gateway.redis.entity.RefreshToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
@PropertySource("classpath:JwtConfig.properties")
public class RefreshTokenService implements RedisService{

    private final RedisTemplate<String, Object> redisTemplate;
    private final String REF_EXPIRATION_TIME;
    private static String KEY_PREFIX = "RefreshToken:";

    public RefreshTokenService(@Autowired RedisTemplate<String, Object> redisTemplate,
                               @Value("${jwt.properties.refresh-token.expiration.time}") String REF_EXPIRATION_TIME) {
        this.redisTemplate = redisTemplate;
        this.REF_EXPIRATION_TIME = REF_EXPIRATION_TIME;
    }

    @Override
    public void setData(Object object) {
        RefreshToken token = (RefreshToken) object;
        Object isAdded = getData(String.valueOf(token.getUserId()));
        if(isAdded == null){
            redisTemplate.opsForValue().set(KEY_PREFIX+token.getUserId(), token, Duration.ofSeconds(Integer.parseInt(REF_EXPIRATION_TIME)/1000));
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
