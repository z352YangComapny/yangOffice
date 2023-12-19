package com.ssoystory.dmservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@RedisHash(value="Message")
public class Message {
    @Id
    private Long id;
    private Long senderId;
    private Long receiverId;
    private String content;
}
