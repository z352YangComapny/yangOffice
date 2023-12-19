package com.ssoystory.dmservice.domain.service;

import com.ssoystory.dmservice.domain.dto.DmMessageOutputDto;
import com.ssoystory.dmservice.domain.repository.DmMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DmServiceImpl implements DmService{
    @Autowired
    private DmMessageRepository dmMessageRepository;

    @Override
    public void save(DmMessageOutputDto dmMessageOutputDto) {
        
    }

    @Override
    public List<DmMessageOutputDto> getPreviousMessages(Long id) {
        return null;
    }
}
