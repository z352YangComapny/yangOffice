package com.ssoystory.feedservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/feed")
public class TestController {
    @GetMapping("/ping")
    ResponseEntity<String> ping(){
        return ResponseEntity.ok("pong");
    }
}