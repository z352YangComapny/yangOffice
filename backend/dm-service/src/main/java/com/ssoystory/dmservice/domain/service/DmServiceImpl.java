package com.ssoystory.dmservice.domain.service;

import com.ssoystory.dmservice.domain.repository.DmMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DmServiceImpl implements DmService{
    @Autowired
    private DmMessageRepository dmMessageRepository;
}
