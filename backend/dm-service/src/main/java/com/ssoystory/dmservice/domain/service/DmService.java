package com.ssoystory.dmservice.domain.service;

import com.ssoystory.dmservice.domain.dto.DmMessageOutputDto;
import com.ssoystory.dmservice.domain.dto.MessagesDto;

import java.util.List;

public interface DmService {
    void save(DmMessageOutputDto dmMessageOutputDto);
    void migrateMessages(List<Object> messageList);
    MessagesDto getPreviousMessage(Long senderId, Long receiverId, Long pageNo);
}
