package com.ssoystory.feedservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/feed")
@Slf4j
public class TestController {
    @GetMapping("/ping")
    ResponseEntity<String> ping(
            @RequestHeader("x-authorization-id") String id,
            @RequestHeader("x-authorization-username") String username,
            @RequestHeader("x-authorization-nickname") String nickname,
            @RequestHeader("x-authorization-authorities") String authorities
    ){
        log.info("id={},username={},nickname={},authorities={}",id,username,nickname,authorities);
        return ResponseEntity.ok("pong");
    }
}