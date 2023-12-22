package com.ssoystory.dmservice.domain.service;

import com.ssoystory.dmservice.domain.dto.DmMessageOutputDto;

import java.util.List;

public interface DmService {
    void save(DmMessageOutputDto dmMessageOutputDto);
    void migrateMessages(List<Object> messageList);
    void getPreviousMessage(Long senderId, Long userId, Long pageNo);
}
