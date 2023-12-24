package com.ssoystory.memberservice.domain.follow.service;

import com.ssoystory.memberservice.domain.follow.dto.FollowOutputDto;

import java.util.List;

public interface FollowService {
    List<FollowOutputDto> getFollowerList(Long userId);

    List<FollowOutputDto> getFolloweeList(Long userId);

    void save(Long userId, Long followeeId);

    void delete(Long userId, Long followeeId);
}
