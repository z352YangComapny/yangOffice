package com.yangworld.app.domain.guestbook.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yangworld.app.domain.guestbook.dto.GuestBookCreateDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookUpdateDto;
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
	public ResponseEntity<?> guestBookCreate(
			@Valid @RequestBody GuestBookCreateDto _guestBook,
			BindingResult bindingResult,
			@AuthenticationPrincipal Member member
			) {
		
		GuestBook guestBook = _guestBook.guestBook();
	
		log.info("_guestBook={}",_guestBook);
		guestBook.setWriterId(member.getId());
		log.info("guestBook={}",guestBook);
		int result = guestBookService.insertGuestBook(guestBook);
		return ResponseEntity.ok(null);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> guestBookDelete(
			@RequestBody Map<String, Integer> requestBody,
			@AuthenticationPrincipal Member member
			) {
		int id = requestBody.get("id");
		GuestBook guestBook = GuestBook.builder()
							.id(id)
							.writerId(member.getId())
							.build();
		log.info("guestBook={}",guestBook);
		int result = guestBookService.deleteGuestBook(guestBook);
		
		return ResponseEntity.ok(null);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> guestBookUpdate(
			@Valid @RequestBody GuestBookUpdateDto _guestBook,
			BindingResult bindingResult,
			@AuthenticationPrincipal Member member
			){
		
		GuestBook guestBook = _guestBook.guestBook();
		log.info("guestBook={}",guestBook);
		guestBook.setWriterId(member.getId());
		int result = guestBookService.updateGuestBook(guestBook);
		
		return ResponseEntity.ok(null);
	}
}
