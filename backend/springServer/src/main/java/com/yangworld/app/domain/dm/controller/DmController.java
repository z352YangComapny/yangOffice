package com.yangworld.app.domain.dm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.dm.dto.DmDetailsDto;
import com.yangworld.app.domain.dm.dto.DmListDto;
import com.yangworld.app.domain.dm.dto.DmSendDto;
import com.yangworld.app.domain.dm.entity.Dm;
import com.yangworld.app.domain.dm.entity.DmRoom;
import com.yangworld.app.domain.dm.service.DmService;
import com.yangworld.app.domain.notification.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/dm")
public class DmController {
	
	@Autowired
	private DmService dmService;
	
	@Autowired
	private NotificationService notificationService;
	
	@GetMapping("/dmCreate")
	public void dmCreate() {}

	/**
	 * DM 선택한 후 대화창 조회
	 */
	@GetMapping("/dmDetailList")
	public ResponseEntity<List<DmDetailsDto>> findDmDetails(@AuthenticationPrincipal PrincipalDetails principal, @RequestParam int dmRoomId) {
	    int userId = principal.getId(); 
	    List<DmDetailsDto> dmDetails = dmService.findDmDetails(dmRoomId, userId);
	    return ResponseEntity.ok(dmDetails);
	}
	
	@GetMapping("/dmDetail")
	public void dmDetail() {}
	
	/**
	 * 가장 최신 dm List 가져오기 
	 */
	@GetMapping("/dmList")
	public void findMyDmList(@AuthenticationPrincipal PrincipalDetails principal, Model model) {
		
	    int userId = principal.getId(); 
	    
	    List<DmListDto> dmList = dmService.findDmRoom(userId);
	    
	    log.info("dmList={}", dmList);
	    
	    model.addAttribute("dmList",dmList);
	    
	 }


	
	@PostMapping("/sendDm")
	public String sendDm(@AuthenticationPrincipal PrincipalDetails principal, @ModelAttribute DmSendDto _dmDto, @RequestParam("dmRoomId") int dmRoomId, @RequestParam String content) {
	    int senderId = principal.getId(); 
	    List<DmRoom> dmRoomList = dmService.findDmRoomById(dmRoomId);
	    DmRoom targetDmRoom = null;

	    for (DmRoom dm : dmRoomList) {
	        if (dm.getParticipant1() == senderId) {
	            targetDmRoom = dm;
	        } else if (dm.getParticipant2() == senderId) {
	        	targetDmRoom = dm;
	        }
	    }
	    	log.info("targetDmRoom={}", targetDmRoom);
	    if (targetDmRoom != null) {
	        Dm msg = _dmDto.toDm();
	        
	        msg.setSenderId(senderId);
	        
	        int receiverId = 0;
	        if(targetDmRoom.getParticipant1() == senderId) {
	        	receiverId = targetDmRoom.getParticipant2();
	        } else if (targetDmRoom.getParticipant2() == senderId) {
	        	receiverId = targetDmRoom.getParticipant1();
	        }
	        msg.setReceiverId(receiverId);
	        msg.setDmRoomId(dmRoomId);
	        
	        log.info("msg = {}", msg);
	        // insert
	        int result = dmService.insertDm(msg);
	        
	        result = notificationService.notifySendDm(msg);
	    }

	    return "redirect:/dm/dmDetail?dmRoomId=" + dmRoomId;
	}

	@PostMapping("/createDmRoom")
	public String insertDmRoom(@AuthenticationPrincipal PrincipalDetails principal, @ModelAttribute DmSendDto _dmDto, @RequestParam int partner) {

	    int participant1 = principal.getId();
	    int participant2 = partner;

	    if (participant1 > participant2) {
	        int temp = participant1;
	        participant1 = participant2;
	        participant2 = temp;
	    }

	    // insert
	    dmService.insertDmRoom(participant1, participant2);

	    List<DmRoom> dmRooms = dmService.findDmRoomById(participant1); // DM Rooms 조회

	    Dm newDm = _dmDto.toDm();

	    if (!dmRooms.isEmpty()) {
	        DmRoom lastDmRoom = dmRooms.get(dmRooms.size() - 1);
	        newDm.setDmRoomId(lastDmRoom.getId()); // 가장 마지막 DM Room의 아이디로 설정
	        newDm.setReceiverId(participant2);
	        newDm.setSenderId(participant1);
	        dmService.insertDm(newDm); // 새로운 DM 생성
	    }

	    return "redirect:/dm/dmList";
	}


	@PostMapping("/deleteDmRoom")
	public String deleteDmRoom(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam int dmRoomId){
		/*
		 * int participant1 = principalDetails.getId(); int participant2 = partner;
		 * 
		 * if (participant1 > participant2) { int temp = participant1; participant1 =
		 * participant2; participant2 = temp; }
		 */
		int result = dmService.deleteDmRoom(dmRoomId);
		/*
		 * if(result>0){ return ResponseEntity.ok().build(); }else { return
		 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); }
		 */
		
		return "redirect:/dm/dmList";
	}
	
	
}
