package com.yangworld.app.domain.guestbook.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.guestbook.dto.GuestBookCreateDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookUpdateDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookWithNicknameDto;
import com.yangworld.app.domain.guestbook.entity.GuestBook;
import com.yangworld.app.domain.guestbook.service.GuestBookService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/guestbook")
public class GuestbookController {
		
	@Autowired
	private GuestBookService guestBookService;
	
	@PostMapping()
	public ResponseEntity<?> guestBookCreate(
			@Valid @RequestBody GuestBookCreateDto _guestBook,
			BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails member
			) {
		int result = guestBookService.insertGuestBook(_guestBook, member);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping()
	public ResponseEntity<?> guestBookDelete(
			@RequestParam int id,
			@AuthenticationPrincipal PrincipalDetails member
			) {
		GuestBook guestBook = GuestBook.builder()
							.id(id)
							.writerId(member.getId())
							.build();
		log.info("guestBook={}",guestBook);
		int result = guestBookService.deleteGuestBook(guestBook);
		if (result>0) {
			return ResponseEntity.ok().build();
		}else{
			return ResponseEntity.status(403).build();
		}
	}
	
	@PatchMapping()
	public ResponseEntity<?> guestBookUpdate(
			@Valid @RequestBody GuestBookUpdateDto _guestBook,
			BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails member
			){
		
		GuestBook guestBook = _guestBook.guestBook();
		log.info("guestBook={}",guestBook);
		guestBook.setWriterId(member.getId());
		int result = guestBookService.updateGuestBook(guestBook);
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> guestBookList(
			@RequestParam String hostname,
			@RequestParam int page
			){
		log.info("page={}",page);
		int limit = 2;
		Map<String, Object> params = Map.of(
				"page",page,
				"limit",limit
			);
		List<GuestBookWithNicknameDto> guestBooks = guestBookService.findAll(params,hostname);
		return ResponseEntity.ok(guestBooks);
	}


	@GetMapping("/count")
	public ResponseEntity<?> getTotalGuestbookCount(
			@RequestParam String hostname
	){
		int totalCount = guestBookService.countAllGuestBook(hostname);
		return ResponseEntity.ok(totalCount);
	}
	
}
