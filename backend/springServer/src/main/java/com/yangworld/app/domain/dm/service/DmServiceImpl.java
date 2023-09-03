package com.yangworld.app.domain.dm.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yangworld.app.domain.dm.dto.DmRoomTextDto;
import com.yangworld.app.domain.dm.entity.DmRoom;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yangworld.app.domain.dm.entity.Dm;
import com.yangworld.app.domain.dm.repository.DmRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class DmServiceImpl implements DmService {

	@Autowired
	private DmRepository dmRepository;
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public int insertDm(Dm dm) {
		int result = dmRepository.insertDm(dm);
		return result;
	}

	@Override
	public List<Dm> findDmDetails(int dmRoomId) {
		List<Dm> dmDetails = dmRepository.findDmDetails(dmRoomId);
		return dmDetails;
	}

	@Override
	public List<DmRoomTextDto> findDmRoomByParticipantId(int id) {
		List<DmRoomTextDto> dmRoomTextDtos = new ArrayList<>();
		log.info("id={}",id);
		List<DmRoom> dmRooms = dmRepository.findDmRoomByParticipantId(id);
		log.info("dmrooms!={}",dmRooms);
		Member member = memberRepository.findById(id);
		for (DmRoom dmRoom: dmRooms ) {
			String participantNickname1;
			String participantNickname2;
			if(dmRoom.getParticipant1() == id){
				participantNickname1 = member.getNickname();
			}else {
				participantNickname1 = memberRepository.findById(dmRoom.getParticipant1()).getNickname();
			}
			if(dmRoom.getParticipant2() == id){
				participantNickname2 = member.getNickname();
			}else {
				participantNickname2= memberRepository.findById(dmRoom.getParticipant2()).getNickname();
			}
			List<Dm> lastDm = dmRepository.findDmDetails(dmRoom.getId());

			DmRoomTextDto dmRoomTextDto =DmRoomTextDto.builder()
					.id(dmRoom.getId())
					.LastTime(lastDm.size()>0 ? lastDm.get(0).getRegDate() : null)
					.participantNickname1(participantNickname1)
					.participantNickname2(participantNickname2)
					.LastMessage(lastDm.size()>0 ? lastDm.get(0).getContent() : null)
					.build();
			dmRoomTextDtos.add(dmRoomTextDto);
		}
		dmRoomTextDtos.sort((o1, o2) ->{
			if (o1.getLastTime() == null || o2.getLastTime() == null) {
				return 0;
			}
			return o2.getLastTime().compareTo(o1.getLastTime());
		} );
		log.info("dmRooms={}",dmRooms);
		return dmRoomTextDtos;
	}

	@Override
	public List<Dm> findMyDmList(int userId) {
		List<Dm> myDms = dmRepository.findMyDmList(userId);
		return myDms;
	}

	@Override
	public void insertDmRoom(int participant1, int participant2) {
		dmRepository.insertDmRoom(participant1, participant2);
	}

	@Override
	public int deleteDmRoom(int dmRoomId) {
		return dmRepository.deleteDmRoom(dmRoomId);
	}

}
