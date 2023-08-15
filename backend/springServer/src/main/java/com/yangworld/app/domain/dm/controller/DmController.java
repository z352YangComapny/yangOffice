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
		// 받는사람이 나임.
	    int receiverId = principal.getId(); 
	    
	    // 내가 받은 DM 조회 -> dm 창 들어가면 나한테 온 dm 리스트 보이도록, 
	    // 그 DM 리스트에서 나한테 보낸 사람 아이디 조회
	    Set<Integer> idList = dmService.findMyDm(receiverId);

	    log.info("myDms={}", idList);
	    // user1:myDms=[4, 9]

	    return ResponseEntity.ok(idList);
	 }


	@GetMapping("/findDmDetails")
	public ResponseEntity<?> findDmDetails(@AuthenticationPrincipal PrincipalDetails principal) {
		int senderId = principal.getId();
		
		// 내가 보낸 DM 조회 
//		List<Dm> dms = dmService.findDmById(senderId);
		
		// Dm 전체조회
		List<Dm> dms = dmService.findDmById(senderId);
		List<Dm> myDms = new ArrayList<>();
		
		for(Dm dm : dms) {
			int receiverId = dm.getReceiverId();
			List<Dm> receiverDm = dmService.findDmDetails(senderId, receiverId);
			myDms.add(dm);
		}
		
		log.info("DmDetails = {}", myDms);
		
		// select receiver_id, sender_id, content, reg_date from dm where
		// (receiver_id=#{receiverId} and sender_id=#{senderId} ) or (receiver_id=#{senderId} and sender_id=#{receiverId});
		
		return ResponseEntity.ok(myDms);
		
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
