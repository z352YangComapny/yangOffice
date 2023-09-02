package com.yangworld.app.domain.profile.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.yangworld.app.commons.HelloSpringUtils;
import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.photoFeed.dto.PhotoFeedAll;
import com.yangworld.app.domain.profile.dto.ProfileAll;
import com.yangworld.app.domain.profile.dto.ProfileDto;
import com.yangworld.app.domain.profile.entity.ProfileDetails;
import com.yangworld.app.domain.profile.entity.State;
import com.yangworld.app.domain.profile.service.ProfileService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/profile/{userName}")
public class ProfileController {

    @Autowired
    private ProfileService profileService;


    @GetMapping("/profile")
    public ResponseEntity<?> feedDetail(@RequestParam String userName) {

        List<ProfileAll> profileDetails = profileService.findProfileAll(userName);

        return ResponseEntity.ok().body(profileDetails);
    }

    @PatchMapping("/updateProfile")
    public ResponseEntity<?> updateProfile(
            @RequestParam int profileId,
            @RequestParam State state,
            @RequestParam String introduction,
            @AuthenticationPrincipal PrincipalDetails member,
            @RequestPart(value = "upFile", required = false) List<MultipartFile> upFiles)
            throws IllegalStateException, IOException {


        int result = profileService.updateProfile(profileId, state, introduction, member, upFiles);

        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/defaultUpdate")
    public ResponseEntity<?> defaultUpdate(
            @AuthenticationPrincipal PrincipalDetails member,
            @RequestParam String userName){
        int result = profileService.defaultUpdateProfile(member, userName);
        return ResponseEntity.ok().body(result);
    }

    @PatchMapping("/createProfile")
    public ResponseEntity<?> createProfile(
            @RequestParam State state,
            @RequestParam String introduction,
            @AuthenticationPrincipal PrincipalDetails member,
            @RequestPart(value = "upFile", required = false) List<MultipartFile> upFiles)
            throws IllegalStateException, IOException {
        int result = profileService.insertProfile(state, introduction, member, upFiles);
        return ResponseEntity.ok().body(result);
    }
}











