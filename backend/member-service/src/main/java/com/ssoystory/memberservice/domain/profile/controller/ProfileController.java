package com.ssoystory.memberservice.domain.profile.controller;

import com.ssoystory.memberservice.domain.profile.entity.Profile;
import com.ssoystory.memberservice.domain.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController("/api/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping
    ResponseEntity<Profile> saveProfile(
            @RequestHeader("x-authorization-id") long id,
            @RequestPart String content,
            @RequestPart int state,
            @RequestPart(value="upFile", required = false) MultipartFile file
            ){
        profileService.save(id,content,state,file);
        return ResponseEntity.ok().build();
    }
}
