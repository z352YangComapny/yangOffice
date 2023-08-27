package com.yangworld.app.oauth.controller;

import com.yangworld.app.oauth.service.GoogleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@RequestMapping("/oauth/google")
public class GoogleController {

    @Autowired
    GoogleService googleService;

    @GetMapping("/login")
    public RedirectView login() {
        return new RedirectView(googleService.getAuthorizeUri());
    }

    @GetMapping("/callback")
    public RedirectView callback(
            @RequestParam String code,
            Model model
    ) {
        return null;

    }


}
