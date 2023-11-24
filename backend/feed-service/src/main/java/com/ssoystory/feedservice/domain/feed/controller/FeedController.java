package com.ssoystory.feedservice.domain.feed.controller;

import com.ssoystory.feedservice.common.kafka.KafkaProducerService;
import com.ssoystory.feedservice.domain.feed.dto.FeedDeleteDto;
import com.ssoystory.feedservice.domain.feed.dto.FeedUpdateDto;
import com.ssoystory.feedservice.domain.feed.entity.PhotoFeed;
import com.ssoystory.feedservice.domain.feed.service.FeedService;
import com.ssoystory.feedservice.exception.ValidationException;
import com.ssoystory.feedservice.exception.feed.CannotFindException;
import com.ssoystory.feedservice.exception.feed.ForbiddenException;
import com.ssoystory.feedservice.exception.s3.S3UploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    ResponseEntity<List<PhotoFeed>> FeedList (@PathVariable String username, @PageableDefault(value= 9, page = 0) Pageable pageable) throws ExecutionException, InterruptedException {
        List<PhotoFeed> photoFeeds = feedService.findPhotoFeedsByAuthorAndPageNO(username, pageable);
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
            @RequestBody FeedUpdateDto feedUpdateDto,
            @RequestHeader("x-authorization-id") Long authId
    ){
        try {
            feedService.update(feedUpdateDto.getId(), feedUpdateDto.getContent(), authId);
            return ResponseEntity.ok().build();
        } catch (ValidationException e){
            log.warn(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }  catch (S3UploadException e) {
            log.warn(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping
    ResponseEntity<?> deleteFeed (
            @RequestHeader("x-authorization-id") Long authId,
            @RequestBody FeedDeleteDto feedDeleteDto
            ){
        try {
            feedService.delete(feedDeleteDto.getId(),authId);
            return ResponseEntity.ok().build();
        } catch (ForbiddenException e) {
            return ResponseEntity.status(403).build();
        } catch (CannotFindException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
