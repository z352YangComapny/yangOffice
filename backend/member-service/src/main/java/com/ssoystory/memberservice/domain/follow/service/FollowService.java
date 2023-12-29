package com.ssoystory.memberservice.domain.follow.service;

import com.ssoystory.memberservice.domain.profile.entity.Profile;

import java.util.List;

public interface FollowService {
    List<Profile> getFollowerList(Long userId);

    List<Profile> getFolloweeList(Long userId);

    void save(Long userId, Long followeeId);

    void delete(Long userId, Long followeeId);
}
