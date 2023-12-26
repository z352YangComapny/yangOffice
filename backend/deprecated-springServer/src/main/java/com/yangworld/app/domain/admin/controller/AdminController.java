package com.yangworld.app.domain.admin.controller;

import com.yangworld.app.domain.guestbook.dto.GuestbookAdminDto;
import com.yangworld.app.domain.guestbook.dto.GuestbookDailyDto;
import com.yangworld.app.domain.guestbook.service.GuestBookService;
import com.yangworld.app.domain.member.dto.UpdateMemberDto;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.service.MemberService;
import com.yangworld.app.domain.photoFeed.dto.FeedDto;
import com.yangworld.app.domain.photoFeed.dto.PhotoFeedDailyDto;
import com.yangworld.app.domain.photoFeed.service.PhotoFeedService;
import com.yangworld.app.domain.profile.dto.AdminProfileDto;
import com.yangworld.app.domain.profile.entity.Profile;
import com.yangworld.app.domain.profile.service.ProfileService;
import com.yangworld.app.domain.story.dto.StoryAdminDto;
import com.yangworld.app.domain.story.dto.StoryDailyDto;
import com.yangworld.app.domain.story.entity.Story;
import com.yangworld.app.domain.story.service.StoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @Autowired
    GuestBookService guestBookService;
    @Autowired
    ProfileService profileService;

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

    @GetMapping("/feed/cli/{pageNo}")
    public ResponseEntity<?> feedCli(@PathVariable int pageNo){
        int pageSize=15;
        List<FeedDto> feed = photoPeedService.getPhotoFeed(pageNo ,pageSize);
        return ResponseEntity.ok(feed);
    }

    @GetMapping("/totalStoryCount")
    public ResponseEntity<?> totalStoryCount(){
        int result = storyService.getTotalStoryCount();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/story/{pageNo}")
    public ResponseEntity<?> story(@PathVariable int pageNo) {
        int pageSize = 10;
        List<StoryAdminDto> result = storyService.getAdminStory(pageNo , pageSize);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/guestbookTotalNo")
    public ResponseEntity<?> guestbookTotalNo(){
        int result = guestBookService.guestbookTotalNo();
        return ResponseEntity.ok(result);
    }
    @GetMapping("/guestbook/{pageNo}")
    public ResponseEntity<?> guestbookList(@PathVariable int pageNo) {
        int pageSize = 10;
        List<GuestbookAdminDto> result = guestBookService.guestbookList(pageNo , pageSize);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/profile/{id}")
    public ResponseEntity<?> profile(@PathVariable int id){
        AdminProfileDto adminProfileDto = profileService.findProfileByMemberIdForAdmin(id);
        return ResponseEntity.ok(adminProfileDto);
    }

    @GetMapping("/guestbook/dailyGuestbook")
    public ResponseEntity<?> dailyGuestbook(){

        List<GuestbookDailyDto> guestbookDailyList = guestBookService.findGuestBookDaily();
        log.info("guestBookDaily={}", guestbookDailyList);
        return ResponseEntity.ok(guestbookDailyList);
    }

    @GetMapping("/photoFeed/dailyPhotoFeed")
    public ResponseEntity<?> dailyPhotoFeed(){
        List<PhotoFeedDailyDto> photoFeedDailyList = photoPeedService.findPhotoFeedDaily();

        return ResponseEntity.ok(photoFeedDailyList);
    }
    @GetMapping("/story/dailyStory")
    public ResponseEntity<?> dailyStory(){
        List<StoryDailyDto> storyDailyList = storyService.findStoryDaily();

        return ResponseEntity.ok(storyDailyList);
    }
}
