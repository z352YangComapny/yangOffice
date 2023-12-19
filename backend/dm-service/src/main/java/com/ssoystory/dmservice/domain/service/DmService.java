package com.ssoystory.dmservice.domain.service;

import com.ssoystory.dmservice.domain.dto.DmMessageInputDto;
import com.ssoystory.dmservice.domain.dto.DmMessageOutputDto;

import java.util.List;

public interface DmService {
    void save(DmMessageOutputDto dmMessageOutputDto);
    List<DmMessageOutputDto> getPreviousMessages(Long id);
}
