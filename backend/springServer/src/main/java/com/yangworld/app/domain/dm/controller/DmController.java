package com.yangworld.app.domain.dm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.dm.dto.DmSendDto;
import com.yangworld.app.domain.dm.entity.Dm;
import com.yangworld.app.domain.dm.service.DmService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/dm")
public class DmController {
	
	@Autowired
	private DmService dmService;
	
	@GetMapping("/findDm")
	public ResponseEntity<?> findDm(@AuthenticationPrincipal PrincipalDetails principal, Model model){
		int senderId = principal.getId();
		
		// 내가 보낸 Dm 조회하기 --> dm 들어가면 모든 대화 뜨게 조회하는 메소드임. view에서 작업 예정.. 아마...
		List<Dm> dms = dmService.findDmById(senderId);
		
		log.info("dms={}", dms);
		/*dms=[Dm(id=1, receiverId=4, senderId=2, content=안녕녕dydhdhd, regDate=2023-08-14T00:08:09),
		Dm(id=2, receiverId=4, senderId=2, content=안녕 모해해, regDate=2023-08-14T09:02:25),
		Dm(id=3, receiverId=4, senderId=2, content=안녕 나는 학언이야야, regDate=2023-08-14T09:02:42)] */
		
		return ResponseEntity.ok(dms);
		
	}
	
	@PostMapping("/sendDm")
	public ResponseEntity<?> sendDm(@AuthenticationPrincipal PrincipalDetails principal, @RequestBody DmSendDto _dmDto) {
		log.info("sendDm info = {}", _dmDto);
		// senderId 가져오기
		
		 int senderId = principal.getId();
		 Dm dm = _dmDto.toDm();
		 log.info("senderId={}", senderId); 
		 dm.setSenderId(senderId);
		 
		// insert
		dmService.insertDm(dm);
		
		return ResponseEntity.ok().build();
	}
	
	
}
