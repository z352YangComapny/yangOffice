package com.yangworld.app.oauth.google.controller;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.config.auth.PrincipalDetailsService;
import com.yangworld.app.domain.member.dto.SignUpDto;
import com.yangworld.app.oauth.google.dto.GoogleInfoResponse;
import com.yangworld.app.oauth.google.service.GoogleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/oauth/google")
public class GoogleController {

    @Autowired
    GoogleService googleService;

    @Autowired
    private PrincipalDetailsService principalDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

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
    public RedirectView handleGoogleCallback(
            @RequestParam("code") String code,
            Model model,
            HttpServletRequest request
    ) throws IOException {

        Map<String, Object> tokens = googleService.getTokens(code);
        log.info("tokens={}", tokens);
        // 세션에 토큰 저장
        model.addAttribute("access_token", tokens.get("access_token"));
        model.addAttribute("refresh_token", tokens.get("refresh_token"));
        // 세션에 토큰 저장
        Map<String, Object> attributes = googleService.getGoogleInfo((String) tokens.get("access_token"));

        String memberId = (String) attributes.get("email");
        PrincipalDetails member = null;
        try {
            member = (PrincipalDetails) principalDetailsService.loadUserByUsername(memberId);
        } catch (UsernameNotFoundException ignore) {
            String name = (String) attributes.get("name");
            log.info("name = {} ", name);

            SignUpDto signUpDto = SignUpDto.builder()
                    .username(memberId)
                    .password(passwordEncoder.encode("1234"))
                    .name(name)
                    .nickname(memberId)
                    .gender("F")
                    .phone("010-8293-1923")
                    .email(memberId)
                    .birthday(LocalDate.parse("2000-01-01"))
                    .build();
            int result = principalDetailsService.insertMember(signUpDto);
            member = (PrincipalDetails) principalDetailsService.loadUserByUsername(memberId);

        }
        // 로그인 처리
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                member,
                member.getPassword(),
                member.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView(request.getContextPath() + "/member/userPage/" + member.getId());
    }
}



