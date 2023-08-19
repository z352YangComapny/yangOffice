package com.yangworld.app.domain.dm.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.dm.dto.DmSendDto;
import com.yangworld.app.domain.dm.entity.Dm;
import com.yangworld.app.domain.dm.entity.DmRoom;
import com.yangworld.app.domain.dm.service.DmService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/dm")
public class DmController {
	
	@Autowired
	private DmService dmService;
	
	@GetMapping("/dmCreate")
	public void dmCreate() {}

	/**
	 * DM 선택한 후 대화창 조회
	 */
	@GetMapping("/dmDetail")
	public void findDmDetails(@AuthenticationPrincipal PrincipalDetails principal, @RequestParam int dmRoomId, Model model) {
		
		 int userId = principal.getId(); 
		
		// dmRoomId 로 찾기 -> 는 서버에서 id값 받아와서 보내야함
		List<Dm> dmDetails = dmService.findDmDetails(dmRoomId);
		
		log.info("dmDetails={}" , dmDetails);
		model.addAttribute("dmDetails", dmDetails);
		model.addAttribute("userId", userId);
	}
	
	/**
	 * 가장 최신 dm List 가져오기 
	 */
	@GetMapping("/dmList")
	public void findMyDmList(@AuthenticationPrincipal PrincipalDetails principal, Model model) {
		
	    int userId = principal.getId(); 
	    
	    List<Dm> myDms = dmService.findMyDmList(userId);
	    Map<Integer, Dm> latestMessagesMap = new HashMap<>();

	    log.info("myDms={}", myDms);
	    
	    for (Dm dm : myDms) {
	        int dmRoomId = dm.getDmRoomId();
	        if (!latestMessagesMap.containsKey(dmRoomId) || dm.getRegDate().isAfter(latestMessagesMap.get(dmRoomId).getRegDate())) {
	            latestMessagesMap.put(dmRoomId, dm);
	        }
	    }

	    // 가장 최신 메시지로 정렬 ( regDate )
	    List<Dm> sortedMessages = new ArrayList<>(latestMessagesMap.values());
	    sortedMessages.sort(Comparator.comparing(Dm::getRegDate).reversed());
	    
	    List<DmRoom> dmRoom = dmService.findDmRoom(userId);
	    
	    log.info("sortedMessages={}", sortedMessages);
	    log.info("myDmRoom={}", dmRoom);
	    model.addAttribute("myDms",myDms);
	    model.addAttribute("dmRoom",dmRoom);
	    model.addAttribute("myDmList", sortedMessages);
	 }


	
	@PostMapping("/sendDm")
	public String sendDm(@AuthenticationPrincipal PrincipalDetails principal, @ModelAttribute DmSendDto _dmDto, @RequestParam("dmRoomId") int dmRoomId) {
	    int senderId = principal.getId(); 
	    List<DmRoom> dmRoomList = dmService.findDmRoom(dmRoomId);
	    DmRoom targetDmRoom = null;

	    for (DmRoom dm : dmRoomList) {
	        if (dm.getParticipant1() == senderId) {
	            targetDmRoom = dm;
	        } else if (dm.getParticipant2() == senderId) {
	        	targetDmRoom = dm;
	        }
	    }

	    if (targetDmRoom != null) {
	        Dm msg = _dmDto.toDm();
	        
	        msg.setSenderId(senderId);
	        msg.setReceiverId(targetDmRoom.getParticipant2());
	        msg.setDmRoomId(dmRoomId);
	        // insert
	        dmService.insertDm(msg);
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

	    List<DmRoom> dmRooms = dmService.findDmRoom(participant1); // DM Rooms 조회

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
		log.info("deleteInfo={}" , dmRoomId);
		int result = dmService.deleteDmRoom(dmRoomId);
		log.info("result = {}",result);
		/*
		 * if(result>0){ return ResponseEntity.ok().build(); }else { return
		 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); }
		 */
		
		return "redirect:/dm/dmList";
	}
	
	
}
