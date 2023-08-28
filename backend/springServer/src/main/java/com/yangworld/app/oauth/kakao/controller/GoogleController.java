package com.yangworld.app.oauth.kakao.controller;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.config.auth.PrincipalDetailsService;
import com.yangworld.app.oauth.kakao.service.GoogleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/oauth/google")
public class GoogleController {

    @Autowired
    GoogleService googleService;

    @Autowired
    private PrincipalDetailsService principalDetailsService;

    @GetMapping("/login")
    public RedirectView login() {
        return new RedirectView(googleService.getAuthorizeUri());
    }

    //    @POSTMapping("/callback")
//    public RedirectView callback(
//            @RequestParam String code,
//            Model model,
//            HttpServletRequest request
//    ) {
//
//        // 1. token
//        Map<String, Object> tokens = googleService.getTokens(code);
//
//        model.addAttribute("access_token", tokens.get("access_token"));
//        model.addAttribute("refresh_token", tokens.get("refresh_token"));
//
//        // 2. 사용자 정보 요청
//        Map<String, Object> attributes = googleService.getInfo((String) tokens.get("access_token"));
//
//        return null;
//    }

    @GetMapping("/callback")
    public String handleGoogleCallback(
            @RequestParam("code") String code
    ) throws IOException {
        // You can also add other necessary parameters based on your requirement.

        System.out.println("Code: " + code);
        log.info("Code: " + code);
        // 4/0Adeu5BUL4ffaq-n1v3ku5rMbVVtnXNlrRpvNJoy-r7Jn6CbjlR72iwjzUujZ5a-Fa583Xg
        googleService.getGoogleInfo(code);

        return "Callback handled successfully!";
    }


}

