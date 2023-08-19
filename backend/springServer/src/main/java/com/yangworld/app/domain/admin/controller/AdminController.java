package com.yangworld.app.domain.admin.controller;

import com.yangworld.app.domain.member.dto.UpdateMemberDto;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.service.MemberService;
import com.yangworld.app.domain.photoFeed.dto.FeedDto;
import com.yangworld.app.domain.photoFeed.service.PhotoFeedService;
import com.yangworld.app.domain.story.service.StoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
@Slf4j
public class AdminController {
    @Autowired
    PhotoFeedService photoPeedService;
    @Autowired
    MemberService memberService;
    @Autowired
    StoryService storyService;

    @GetMapping("/photoFeedCount")
    public ResponseEntity<?> photoFeedTotalCount(){
        int result = photoPeedService.getPhotoFeedTotalCount();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/memberCount")
    public ResponseEntity<?> memberTotalCount(){
        int result = memberService.memberTotalCount();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/memberPage/{pageNo}")
    public ResponseEntity<?> memberPage(@PathVariable int pageNo) {
        int pageSize = 10;
        List<Member> memberList = memberService.getMemberPage(pageNo, pageSize);
        return ResponseEntity.ok(memberList);
    }

    @PostMapping("/memberUpdate")
    public ResponseEntity<?> memberPage(@RequestBody UpdateMemberDto memberUpdate) {
        int result = memberService.updateMemberByAdmin(memberUpdate);
        return ResponseEntity.ok("");
    }

    @GetMapping("/feed/{pageNo}")
    public ResponseEntity<?> feed(@PathVariable int pageNo){
        int pageSize=5;
        List<FeedDto> feed = photoPeedService.getPhotoFeed(pageNo ,pageSize);
        return ResponseEntity.ok(feed);
    }

    @GetMapping("/totalStoryCount")
    public ResponseEntity<?> totalStoryCount(){
        int result = storyService.getTotalStoryCount();
        return ResponseEntity.ok(result);
    }
}
