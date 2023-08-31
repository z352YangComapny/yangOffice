package com.yangworld.app.domain.dm.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.dm.dto.DmDetailsDto;
import com.yangworld.app.domain.dm.dto.DmListDto;
import com.yangworld.app.domain.dm.entity.Dm;
import com.yangworld.app.domain.dm.entity.DmRoom;
import com.yangworld.app.domain.dm.repository.DmRepository;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.profile.entity.AttachmentProfile;
import com.yangworld.app.domain.profile.entity.Profile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class DmServiceImpl implements DmService {

	@Autowired
	private DmRepository dmRepository;
	
	@Override
	public int insertDm(Dm dm) {
		int result = dmRepository.insertDm(dm);
		return result;
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
		int result =  dmRepository.deleteDmRoom(dmRoomId);
		return result;
	}
	
	@Override
	public String getUsernameById(int Id) {
		return dmRepository.getUsernameById(Id);
	}
	
	@Override
	public List<DmRoom> findDmRoomByDmRoomId(int dmRoomId) {
		return dmRepository.findDmRoomByDmRoomId(dmRoomId);
	}
	
	@Override
	public List<DmRoom> findDmRoomById(int participant1) {
		return dmRepository.findDmRoomById(participant1);
	}
	
	@Override
	public List<DmDetailsDto> findDmDetails(int dmRoomId, int userId) {
		
		List<Dm> dmDetails = dmRepository.findDmDetails(dmRoomId);
		List<DmDetailsDto> dmDto= new ArrayList<>();
		int otherId = 0;
		
		for(Dm dm : dmDetails) {
			
			if(dm.getReceiverId() == userId) {
				otherId = dm.getSenderId();
			} else if(dm.getSenderId() == userId) {
				otherId = dm.getReceiverId();
			}
			
			Member member = dmRepository.findUsernameForDm(otherId);
			String name = member.getName();
			
			Profile profile = dmRepository.findProfileIdForDm(otherId);
			int profileId = profile.getId();
			
			
			AttachmentProfile attachProfile = dmRepository.findAttachIdForDm(profileId);
			int attachId = attachProfile.getAttachmentId();
			
			Attachment attach = dmRepository.findRenameFileNameForDm(attachId);
			String renamedFileName = attach.getRenamedFilename();
			
			
			DmDetailsDto dmDetailsDto = DmDetailsDto.builder()
												.id(dm.getId()).content(dm.getContent()).regDate(dm.getRegDate())
														.receiverId(dm.getReceiverId()).senderId(dm.getSenderId()).dmRoomId(dm.getDmRoomId())
														.name(name).renamedFileName(renamedFileName).build();
			
			dmDto.add(dmDetailsDto);
			
		}
		
		return dmDto;
	}
	
	
	@Override
	public List<DmListDto> findDmRoom(int userId) {
		
		// participant1, 2, dmRoomId
		List<DmRoom> dmRoom = dmRepository.findDmRoom(userId);
		List<DmListDto> dmDtoList = new ArrayList<>();
		
		for(DmRoom dms : dmRoom) {
			int dmRoomId = dms.getId();
			int otherId = 0;
			
			// content, regDate
			List<Dm> dm = dmRepository.findDmContent(dmRoomId);
			
			dm.sort(Comparator.comparing(Dm::getRegDate).reversed());
			
			for(Dm msg : dm) {
			
			if(dms.getParticipant1() == userId) {
				 otherId = dms.getParticipant2();
				 
			} else if(dms.getParticipant2() == userId) {
				otherId = dms.getParticipant1();
			}
			
			// username 
			Member member = dmRepository.findUsernameForDm(otherId);
			String name = member.getName();
			String nickname = member.getNickname();
			log.info("otherId={}", otherId);
			Profile profile = dmRepository.findProfileIdForDm(otherId);
				int profileId = profile.getId();
				System.out.println("profileId= " + profileId);
				
				
				AttachmentProfile attachProfile = dmRepository.findAttachIdForDm(profileId);
				int attachId = attachProfile.getAttachmentId();
				
				Attachment attach = dmRepository.findRenameFileNameForDm(attachId);
				String renamedFileName = attach.getRenamedFilename();
				
				
				DmListDto dmListDto = DmListDto.builder()
						.name(name)
						.nickname(nickname)
						.renamedFileName(renamedFileName)
						.content(msg.getContent())
						.dmRoomId(dmRoomId)
						.participant1(dms.getParticipant1())
						.participant2(dms.getParticipant2())
						.regDate(msg.getRegDate()) // regDate를 dm에서 가져와야 함
						.build();
				
				dmDtoList.add(dmListDto);
			
				}
		    }
		    
		dmDtoList.sort(Comparator.comparing(DmListDto::getRegDate).reversed());
		
		    return dmDtoList;
		}
	
	@Override
	public List<Map<String, Object>> findMemberId(int dmRoomId) {
		return dmRepository.findMemberId(dmRoomId);
	}

	@Override
	public Dm findMyNewDm(int userId) {
		return dmRepository.findMyNewDm(userId);
	}

}
