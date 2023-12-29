package com.ssoystory.memberservice.domain.profile.controller;

import com.ssoystory.memberservice.domain.profile.dto.GetProfileDto;
import com.ssoystory.memberservice.domain.profile.entity.Profile;
import com.ssoystory.memberservice.domain.profile.service.ProfileService;
import com.ssoystory.memberservice.exception.MemberNotFoundException;
import com.ssoystory.memberservice.exception.ProfileNotFoundException;
import com.ssoystory.memberservice.exception.S3UploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController("/api/profile")
@Slf4j
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping
    ResponseEntity<Profile> getProfile(
            @RequestBody GetProfileDto getProfileDto
    ){
        try {
            Profile profile = profileService.findByUsername(getProfileDto.getUsername());
            return ResponseEntity.ok().body(profile);
        } catch (ProfileNotFoundException exception){
            return ResponseEntity.internalServerError().build();
        } catch ( MemberNotFoundException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    ResponseEntity<Profile> saveProfile(
            @RequestHeader("x-authorization-id") long id,
            @RequestHeader("x-authorization-username") String username,
            @RequestPart String content,
            @RequestPart int state,
            @RequestPart(value="upFile", required = false) MultipartFile file
            ){
        try {
            Profile profile= profileService.save(id, content, state, file, username);
            return ResponseEntity.ok().body(profile);
        } catch (S3UploadException exception ) {
            log.info(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        } catch (IOException exception){
            log.info(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    ResponseEntity<Profile> updateProfile(
            @RequestHeader("x-authorization-id") long id,
            @RequestHeader("x-authorization-username") String username,
            @RequestPart String content,
            @RequestPart int state,
            @RequestPart(value="upFile", required = false) MultipartFile file,
            @RequestPart Long postId
    ){
        try {
            Profile profile = profileService.update(id, username, content, state, file, postId);
            return ResponseEntity.ok().body(profile);
        } catch (S3UploadException exception ) {
            log.info(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        } catch (IOException exception){
            log.info(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        } catch (ProfileNotFoundException exception){
            log.info(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
