package com.ssoystory.memberservice.domain.follow.service;

import com.ssoystory.memberservice.domain.follow.dto.FollowOutputDto;
import com.ssoystory.memberservice.domain.follow.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService{
    @Autowired
    private FollowRepository followRepository;

    @Override
    public List<FollowOutputDto> getFollowerList(Long userId) {
        return null;
    }

    @Override
    public List<FollowOutputDto> getFolloweeList(Long userId) {
        return null;
    }

    @Override
    public void save(Long userId, Long followeeId) {
        
    }

    @Override
    public void delete(Long userId, Long followeeId) {

    }
}
