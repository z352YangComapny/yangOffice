package com.yangworld.app.domain.admin.controller;

import com.yangworld.app.domain.member.service.MemberService;
import com.yangworld.app.domain.photoFeed.service.PhotoFeedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
@Slf4j
public class AdminController {
    @Autowired
    PhotoFeedService photoPeedService;
    @Autowired
    MemberService memberService;

    @GetMapping("/photoFeedCount")
    public ResponseEntity<?> photoFeedTotalCount(){
        int result = photoPeedService.getPhotoFeedTotalCount();
        log.info("result={}",result);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/memberCount")
    public ResponseEntity<?> memberTotalCount(){
        int result = memberService.memberTotalCount();
        log.info("result={}",result);
        return ResponseEntity.ok(result);
    }

}
