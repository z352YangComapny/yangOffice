package com.ssoystory.guestbookservice.domain.guestbook.controller;

import com.ssoystory.guestbookservice.domain.guestbook.dto.DeleteGuestbookDto;
import com.ssoystory.guestbookservice.domain.guestbook.dto.GuestbookDto;
import com.ssoystory.guestbookservice.domain.guestbook.entity.Guestbook;
import com.ssoystory.guestbookservice.domain.guestbook.service.GuestbookService;
import com.ssoystory.guestbookservice.exception.AuthorizationException;
import com.ssoystory.guestbookservice.exception.CannotFindGuestbookException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api/guestbook")
public class GuestbookController {
    @Autowired
    private GuestbookService guestbookService;
    @PostMapping
    public ResponseEntity<?> createGuestBook(
            @RequestHeader("x-authorization-id") Long authId,
            @RequestBody GuestbookDto guestbookDto){
        try {
            guestbookService.save(authId,guestbookDto);
            return ResponseEntity.ok().build();
        } catch (InterruptedException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> getPostList(
            @RequestParam String username,
            @PageableDefault(value= 2, page = 0) Pageable pageable
    ){
        Page<Guestbook> list = null;
        try {
            list = guestbookService.findById(username,pageable);
            return ResponseEntity.ok().body(list);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletePost(
            @RequestHeader("x-authorization-id") Long authId,
            @RequestBody DeleteGuestbookDto guestbookDto
    ){
        try {
            guestbookService.delete(authId, guestbookDto);
            return ResponseEntity.ok().build();
        }catch (CannotFindGuestbookException e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }catch (AuthorizationException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(403).build();
        }
    }
}
