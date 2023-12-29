package com.ssoystory.memberservice.domain.profile.service;

import com.ssoystory.memberservice.domain.profile.entity.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProfileService {
    Profile save(long id, String content, int state, MultipartFile file, String username) throws IOException;

    Profile update(long id, String username, String content, int state, MultipartFile file, Long postId) throws IOException;

    Profile findByUsername(String username);
}
