package com.yangworld.app.domain.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.chat.dto.SendChatDto;
import com.yangworld.app.domain.chat.entity.Chat;
import com.yangworld.app.domain.chat.service.ChatService;
import com.yangworld.app.domain.notification.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/chat")
@Slf4j
public class ChatController {

    @Autowired
    private ChatService chatService;

	@Autowired
	private NotificationService notificationService;

	/*
	 * @GetMapping("/chatting") public void chatting () { }
	 */
    
    @GetMapping("/chatting")
    public void chatList(@AuthenticationPrincipal PrincipalDetails principal, Model model) { }
    
    @PostMapping("/sendChat") 
    public String sendChat(@AuthenticationPrincipal PrincipalDetails principal, @ModelAttribute SendChatDto _sendChatDto) {
    	
    	int memberId = principal.getId();
    	Chat chat = _sendChatDto.toChat();
    	chat.setMemberId(memberId);
    	
    	int result = chatService.sendChat(chat);
    	
    	return "redirect:/chat/chatting";
    }
    
    


}
