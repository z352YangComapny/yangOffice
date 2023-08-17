package com.yangworld.app.domain.dm.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;

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

	/**
	 * DM 선택한 후 대화창 조회
	 */
	@GetMapping("/findDmDetails")
	public void findDmDetails(@AuthenticationPrincipal PrincipalDetails principal, @RequestParam int dmRoomId, Model model) {
		
		// dmRoomId 로 찾기 -> 는 서버에서 id값 받아와서 보내야함
		List<Dm> dmDetails = dmService.findDmDetails(dmRoomId);
		
		model.addAttribute("dmDetails", dmDetails);
	}
	
	/**
	 * 가장 최신 dm List 가져오기 
	 */
	@GetMapping("/findMyDmList")
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

	    model.addAttribute("myDmList", sortedMessages);
	 }


	
	@PostMapping("/sendDm")
	public String sendDm(@AuthenticationPrincipal PrincipalDetails principal, @RequestBody DmSendDto _dmDto) {
		log.info("sendDm info = {}", _dmDto);
		 int senderId = principal.getId();
		 Dm dm = _dmDto.toDm();
		 log.info("senderId={}", senderId);
		 dm.setSenderId(senderId);

		// insert
		dmService.insertDm(dm);
		
		return "redirect:/dm/dmDetail.do?id=" + dm.getSenderId();
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
