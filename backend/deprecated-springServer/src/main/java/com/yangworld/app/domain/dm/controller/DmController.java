package com.yangworld.app.domain.dm.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yangworld.app.domain.dm.dto.DmRoomDto;
import com.yangworld.app.domain.dm.dto.DmRoomTextDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
	 * GET : http://localhost:8080/dm/findDmDetails
	 * Key : dmRoomId
	 * value : {dmRoomId}
	 * - Headers : Authorization ** 필수
	 */
	@GetMapping("/findDmDetails")
	public ResponseEntity<?> findDmDetails(@AuthenticationPrincipal PrincipalDetails principal, @RequestParam int dmRoomId) {
		
		// dmRoomId 로 찾기 -> 는 서버에서 id값 받아와서 보내야함
		List<Dm> dmDetails = dmService.findDmDetails(dmRoomId);
		
		return ResponseEntity.ok(dmDetails);
	}

	/**
	 * GET : http://localhost:8080/dm/findMyDmList
	 * - Headers : Authorization ** 필수
	 */
	@GetMapping("/findMyDmList")
	public ResponseEntity<?> findMyDmList(@AuthenticationPrincipal PrincipalDetails principal, Model model) {
		
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

	    return ResponseEntity.ok(sortedMessages);
	 }


	/**
	 * POST : http://localhost:8080/dm/sendDm
	 * Key : receiverId(view) , content, dmRoomId (view)
	 * value : {receiverId, content ,dmRoomId}
	 * - Headers : Authorization ** 필수
	 */
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


	@GetMapping("/roomList")
	public ResponseEntity<?> findDmRoomByParticipantId(@AuthenticationPrincipal PrincipalDetails principalDetails){
		List<DmRoomTextDto> dmRoomTextDtos = dmService.findDmRoomByParticipantId(principalDetails.getId());
		return ResponseEntity.ok().body(dmRoomTextDtos);
	}

	/**
	 * POST : http://localhost:8080/dm/createDmRoom
	 * Key : partner (view)
	 * value : {partner}
	 * - Headers : Authorization ** 필수
	 */
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

	/**
	 * DELETE : http://localhost:8080/dm/deleteDmRoom
	 * Key : dmRoomId (view)
	 * value : {dmRoomId}
	 * - Headers : Authorization ** 필수
	 */
	@DeleteMapping ("/deleteDmRoom")
	public ResponseEntity<?> deleteDmRoom(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam int dmRoomId /*@RequestBody Map<String, Integer> map*/){
/*		int participant1 = principalDetails.getId();
		int participant2 = map.get("partner");

		if (participant1 > participant2) {
			int temp = participant1;
			participant1 = participant2;
			participant2 = temp;
		}*/

		int result = dmService.deleteDmRoom(dmRoomId);
		if(result>0){
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
}
