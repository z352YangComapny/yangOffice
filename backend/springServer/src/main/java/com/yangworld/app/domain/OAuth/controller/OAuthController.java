package com.yangworld.app.domain.OAuth.controller;

import com.yangworld.app.domain.OAuth.dto.OAuthDto;
import com.yangworld.app.domain.OAuth.service.OAuthService;
import com.yangworld.app.domain.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth")
@Slf4j
public class OAuthController {
    @Autowired
    MemberService memberService;

    @Autowired
    OAuthService oAuthService;

    @PostMapping("")
    public ResponseEntity<?> oauthhandler( @RequestBody OAuthDto oAuthDto){
        log.info("oAuthDto={}",oAuthDto);
        switch (oAuthDto.getProvider()) {
            case "kakao" :
                String[] result = oAuthService.kakao(oAuthDto);
                return ResponseEntity.ok().body(result);
            default:
                return ResponseEntity.status(500).build();
        }

    }
}
