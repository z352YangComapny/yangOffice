package com.ssoystory.dmservice.common.redis.service;

import com.ssoystory.dmservice.domain.dto.DmMessageOutputDto;

public interface RedisService {
    void save(DmMessageOutputDto dmMessageOutputDto);

    void migrateMessages(Long id);
}
