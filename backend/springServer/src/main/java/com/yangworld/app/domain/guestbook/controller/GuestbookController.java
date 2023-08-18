package com.yangworld.app.domain.guestbook.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yangworld.app.config.auth.PrincipalDetails;
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
	
	@PostMapping("/create.do")
	public String guestBookCreate(
			@Valid GuestBookCreateDto _guestBook,
			BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails member
			) {
//		if (member == null) {
//	        return "redirect:/member/memberLogin.do";
//	    }
		
		log.debug("member={}",member);
		GuestBook guestBook = _guestBook.guestBook();
	
		log.info("_guestBook={}",_guestBook);
		guestBook.setWriterId(member.getId());
		log.info("guestBook={}",guestBook);
		int result = guestBookService.insertGuestBook(guestBook);
		return "redirect:/guestbook";
	}
	
	@PostMapping("/delete.do")
	public String guestBookDelete(
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
		
		return "redirect:/guestbook";
	}
	
	@PostMapping("/update.do")
	public String guestBookUpdate(
			@Valid @RequestBody GuestBookUpdateDto _guestBook,
			BindingResult bindingResult,
			@AuthenticationPrincipal Member member
			){
		
		GuestBook guestBook = _guestBook.guestBook();
		log.info("guestBook={}",guestBook);
		guestBook.setWriterId(member.getId());
		int result = guestBookService.updateGuestBook(guestBook);
		
		return "redirect:/guestbook";
	}
	
	@GetMapping("/guestbook")
	public void guestBookList(
			@RequestParam(defaultValue = "1") int page,
			@AuthenticationPrincipal Member member,
			Model model
			){
		int limit = 5;
		Map<String, Object> params = Map.of(
				"page",page,
				"limit",limit
			);
		log.info("member ={} ",member);
		List<GuestBook> guestBooks = guestBookService.findAll(params);
		model.addAttribute("guestBooks",guestBooks);
	}
}
