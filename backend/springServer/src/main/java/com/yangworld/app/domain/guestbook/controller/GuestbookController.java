package com.yangworld.app.domain.guestbook.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yangworld.app.domain.guestbook.dto.GuestBookDto;
import com.yangworld.app.domain.guestbook.entity.GuestBook;
import com.yangworld.app.domain.guestbook.service.GuestBookService;
import com.yangworld.app.domain.member.entity.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/guestbook")
public class GuestbookController {
		
	@Autowired
	private GuestBookService guestBookService;
	
	@PostMapping("/create")
	public String guestBookCreate(
			@Valid @RequestBody GuestBookDto _guestBook,
			BindingResult bindingResult,
			@AuthenticationPrincipal Member member,
			RedirectAttributes redirectAttr) {
		
		GuestBook guestBook = _guestBook.guestBook();
	
		log.info("_guestBook={}",_guestBook);
		guestBook.setWriterId(member.getId());
		log.info("guestBook={}",guestBook);
		int result = guestBookService.insertGuestBook(guestBook);
		return "redirect:/guestBook/guestBook.do";
	}
	
}
