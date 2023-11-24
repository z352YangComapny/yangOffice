package com.ssoystory.memberservice.domain.profile.service;

import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {
    void save(long id, String content, int state, MultipartFile file);
}
