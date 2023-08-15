package com.yangworld.app.domain.dm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.dm.dto.DmRoomDto;
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
	
	@GetMapping("/findMyDm")
	public ResponseEntity<?> findDm(@AuthenticationPrincipal PrincipalDetails principal, Model model) {
		
	    int userId = principal.getId(); 
	    
	    // 내 dm목록 조회
	    // 잘 뜨는데 id 값이 0 이 나옴..왜지 근데 id 값 필요없음 . dmroomId 쓰면되니까..?
	   	 List<Dm> myDms = dmService.findMyDm(userId);

	    log.info("myDms={}", myDms);
	    // user1:myDms=[4, 9]

	    return ResponseEntity.ok(myDms);
	 }


	
	@PostMapping("/sendDm")
	public ResponseEntity<?> sendDm(@AuthenticationPrincipal PrincipalDetails principal, @RequestBody DmSendDto _dmDto) {
		log.info("sendDm info = {}", _dmDto);
		 int senderId = principal.getId();
		 Dm dm = _dmDto.toDm();
		 log.info("senderId={}", senderId);
		 dm.setSenderId(senderId);

		// insert
		dmService.insertDm(dm);
		
		return ResponseEntity.ok().build();
	}

	@PostMapping("/createDmRoom")
	public ResponseEntity<?> insertDmRoom(@AuthenticationPrincipal PrincipalDetails principal, @RequestBody Map<String, Integer> participants) {
		log.debug("DmRoomDto info = {}", participants);

		int participant1 = principal.getId();
		int participant2 = participants.get("partner");

		if (participant1 > participant2) {
			int temp = participant1;
			participant1 = participant2;
			participant2 = temp;
		}
		log.debug("participants={},{}", participant1, participant2);
		// insert
		dmService.insertDmRoom(participant1, participant2);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/deleteDmRoom")
	public ResponseEntity<?> deleteDmRoom(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody Map<String, Integer> map){
		int participant1 = principalDetails.getId();
		int participant2 = map.get("partner");

		if (participant1 > participant2) {
			int temp = participant1;
			participant1 = participant2;
			participant2 = temp;
		}

		int result = dmService.deleteDmRoom(participant1, participant2);
		if(result>0){
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
}
