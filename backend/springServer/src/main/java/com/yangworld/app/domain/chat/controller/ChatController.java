package com.yangworld.app.domain.chat.controller;

import com.yangworld.app.domain.chat.entity.Chat;
import com.yangworld.app.domain.chat.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/chat")
@Slf4j
public class ChatController {

    @Autowired
    private ChatService chatService;





}
