package com.yangworld.app.domain.guestbook.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.guestbook.dto.GuestBookCreateDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookDeleteDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookUpdateDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookWithNicknameDto;
import com.yangworld.app.domain.guestbook.entity.GuestBook;
import com.yangworld.app.domain.guestbook.service.GuestBookService;
import com.yangworld.app.domain.member.entity.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/userPage/{id}/guestbook")
public class GuestbookController {
		
	@Autowired
	private GuestBookService guestBookService;
	
	@PostMapping("/create.do")
	public String guestBookCreate(
			@Valid GuestBookCreateDto guestBook,
			BindingResult bindingResult,
			RedirectAttributes redirectAttr,
			@PathVariable int id,
			@AuthenticationPrincipal PrincipalDetails member
			) {
//		if (member == null) {
//	        return "redirect:/member/memberLogin.do";
//	    }
		
		log.info("memberid={}",id);
		//GuestBook guestBook = _guestBook.guestBook();
		guestBook.setMemberId(id);
	
		log.info("guestBook={}",guestBook);
		guestBook.setWriterId(member.getId());
		
		log.info("guestbook={}", guestBook.getWriterId());
		
		int result = guestBookService.insertGuestBook(guestBook);
		log.info("result@create={}", result);
		
		return "redirect:/member/userPage/{id}/guestbook/guestbook";
	}
	
	@PostMapping("/delete.do")
	public ResponseEntity<?> guestBookDelete(
			@RequestParam int deleteGuestbook,
			@AuthenticationPrincipal Member member,
			@Valid  GuestBookDeleteDto delete
			) {
		int id = member.getId();
		
		GuestBook guestBook = GuestBook.builder()
							.id(id)
							.writerId(member.getId())
							.build();
		
		log.info("guestBook={}",guestBook);
		delete.setId(deleteGuestbook);
		delete.setWriterId(id);
		log.info("delete={}",delete);
		int result = guestBookService.deleteGuestBook(delete);
		log.info("result={}",result);
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("result", result));
	}
	
	@PostMapping("/update.do")
	public ResponseEntity<?> guestBookUpdate(
			GuestBookUpdateDto updateDto,
			@RequestParam int updateGuestbook,
			@RequestParam String content,
			BindingResult bindingResult,
			@AuthenticationPrincipal Member member
			){
		
		GuestBook guestBook = updateDto.guestBook();
		log.info("guestBook={}",guestBook);
		guestBook.setWriterId(member.getId());
		
		updateDto.setId(updateGuestbook);
		updateDto.setContent(content);
		log.info("_guestBook={}",updateDto);
		int result = guestBookService.updateGuestBook(updateDto);
		
		log.info("result={}",result);
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("result", result));
	}
	
	@GetMapping("/guestbook")
	public String guestBookList(
			@RequestParam(defaultValue = "1") int page,
			@AuthenticationPrincipal Member member,
			@PathVariable int id,
			Model model
			){
		int limit = 5;
		Map<String, Object> params = Map.of(
				"page",page,
				"limit",limit,
				"id",member.getId()
			);
		log.info("member ={} ",member);
		int memberId = member.getId();
		
		  	int totalCount = guestBookService.countAllGuestbook(memberId); // 전체 데이터 개수 조회
		    log.info("totlaCount@guest={}",totalCount);
		  	int totalPages = (int) Math.ceil((double) totalCount / limit); // 총 페이지 개수 계산
		    log.info("totalPage={}", totalPages);
		  	
		  	
		List<GuestBookWithNicknameDto> guestBooks = guestBookService.findAll(params);
		log.info("guestBooks={}",guestBooks);
		model.addAttribute("guestBooks",guestBooks);
		model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", totalPages);

		return "guestbook/guestbook";

	}
}
