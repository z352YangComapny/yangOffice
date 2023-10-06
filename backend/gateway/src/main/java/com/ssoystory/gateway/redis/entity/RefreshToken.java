package com.ssoystory.gateway.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash(value="RefreshToken" , timeToLive = 1209600)
public class RefreshToken {
    @Id
    private int userId;
    private String tokenVal;
}