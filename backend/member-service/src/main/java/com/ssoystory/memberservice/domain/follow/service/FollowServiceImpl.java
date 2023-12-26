package com.ssoystory.memberservice.domain.follow.service;

import com.ssoystory.memberservice.domain.follow.entity.Follow;
import com.ssoystory.memberservice.domain.follow.entity.FollowKey;
import com.ssoystory.memberservice.domain.follow.repository.FollowRepository;
import com.ssoystory.memberservice.domain.profile.entity.Profile;
import com.ssoystory.memberservice.domain.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService{
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public List<Profile> getFollowerList(Long userId) {
        return profileRepository.getFollowerList(userId);
    }

    @Override
    public List<Profile> getFolloweeList(Long userId) {
        return profileRepository.getFolloweeList(userId);
    }

    @Override
    public void save(Long userId, Long followeeId) {
        FollowKey followKey = FollowKey.builder()
                .followerId(userId)
                .followeeId(followeeId)
                .build();
        Follow follow = Follow.builder()
                .id(followKey)
                .build();
        followRepository.save(follow);
    }

    @Override
    public void delete(Long userId, Long followeeId) {
        FollowKey followKey = FollowKey.builder()
                .followerId(userId)
                .followeeId(followeeId)
                .build();
        followRepository.deleteById(followKey);
    }
}
