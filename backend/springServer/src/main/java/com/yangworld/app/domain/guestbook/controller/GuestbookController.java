package com.yangworld.app.domain.guestbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yangworld.app.domain.guestbook.service.GuestBookService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class GuestbookController {
		
	@Autowired
	private GuestBookService guestBookService;
	
}
