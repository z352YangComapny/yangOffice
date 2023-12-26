package com.ssoystory.memberservice.domain.follow.controller;

import com.ssoystory.memberservice.domain.follow.dto.FollowDto;
import com.ssoystory.memberservice.domain.follow.service.FollowService;
import com.ssoystory.memberservice.domain.profile.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/follow")
public class FollowController {
    @Autowired
    private FollowService followService;

    @PostMapping
    ResponseEntity<?> follow(FollowDto followDto) {
        followService.save(followDto.getUserId() , followDto.getFolloweeId());
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    ResponseEntity<?> cancelFollowing(FollowDto followDto) {
        followService.delete(followDto.getUserId(), followDto.getFolloweeId());
        return ResponseEntity.ok().build();
    }
    @GetMapping("/er")
    ResponseEntity<List<Profile>> getFollowerList(Long userId){
        List<Profile> list = followService.getFollowerList(userId);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/ee")
    ResponseEntity<List<Profile>> getFolloweeList(Long userId){
        List<Profile> list = followService.getFolloweeList(userId);
        return ResponseEntity.ok().body(list);
    }
}
