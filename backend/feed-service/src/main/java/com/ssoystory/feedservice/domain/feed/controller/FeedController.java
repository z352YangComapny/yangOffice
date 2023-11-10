package com.ssoystory.feedservice.domain.feed.controller;

import com.ssoystory.feedservice.common.kafka.KafkaProducerService;
import com.ssoystory.feedservice.domain.feed.entity.PhotoFeed;
import com.ssoystory.feedservice.domain.feed.service.FeedService;
import com.ssoystory.feedservice.exception.ValidationException;
import com.ssoystory.feedservice.exception.s3.S3UploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/feed")
@Slf4j
public class FeedController {
    @Autowired
    private FeedService feedService;
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @GetMapping("/list/{username}")
    ResponseEntity<List<PhotoFeed>> FeedList (@PathVariable String username, int pageNo) throws ExecutionException, InterruptedException {
        List<PhotoFeed> photoFeeds = feedService.findPhotoFeedsByAuthorAndPageNO(username);
        return ResponseEntity.ok(photoFeeds);
    }

    @PostMapping
    ResponseEntity<?> saveFeed(
            @RequestPart String content,
            @RequestPart(value = "upFile", required = false) List<MultipartFile> upFiles,
            @RequestHeader("x-authorization-username") String auth,
            @RequestHeader("x-authorization-id") Long authId
    ){
        try {
            if (content == null || content.isEmpty() || auth == null || auth.isEmpty() || authId == null) {
                throw new ValidationException("Invalid request parameters");
            }
            feedService.save(content, upFiles, auth, authId);
            return ResponseEntity.ok().build();
        } catch (ValidationException e){
            log.warn(e.getMessage());
            return ResponseEntity.internalServerError().build();
        } catch (S3UploadException e) {
            log.warn(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    ResponseEntity<?> updatedFeed(
            @RequestPart String content,
            @RequestPart(value = "upFile", required = false) List<MultipartFile> upFiles,
            @RequestHeader("x-authorization-username") String auth,
            @RequestHeader("x-authorization-id") Long authId
    ){
        try {
            if (content == null || content.isEmpty() || auth == null || auth.isEmpty() || authId == null) {
                throw new ValidationException("Invalid request parameters");
            }
            feedService.update(content, upFiles, auth, authId);
            return ResponseEntity.ok().build();
        } catch (ValidationException e){
            log.warn(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }  catch (S3UploadException e) {
            log.warn(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
