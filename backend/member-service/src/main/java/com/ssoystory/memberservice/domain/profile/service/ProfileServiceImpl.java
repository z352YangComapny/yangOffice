package com.ssoystory.memberservice.domain.profile.service;

import com.ssoystory.memberservice.domain.profile.entity.Profile;
import com.ssoystory.memberservice.domain.profile.entity.ProfileId;
import com.ssoystory.memberservice.domain.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileServiceImpl implements ProfileService{
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public void save(long id, String content, int state, MultipartFile file) {
        ProfileId profileId = ProfileId.builder()
                .MemberId(id)
                .build();
        Profile profile = Profile.builder()
                .embeddedProfileId(profileId)
                .content(content)
                .state(state)
                .build();
    }
}
