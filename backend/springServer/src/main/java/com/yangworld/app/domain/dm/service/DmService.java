package com.yangworld.app.domain.dm.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yangworld.app.domain.dm.dto.DmDetailsDto;
import com.yangworld.app.domain.dm.dto.DmListDto;
import com.yangworld.app.domain.dm.dto.DmSendDto;
import com.yangworld.app.domain.dm.entity.Dm;
import com.yangworld.app.domain.dm.entity.DmRoom;

public interface DmService {

	int insertDm(Dm dm);

	List<Dm> findMyDmList(int userId);

    void insertDmRoom(int participant1, int participant2);

	int deleteDmRoom(int dmRoomId);

	List<DmDetailsDto> findDmDetails(int dmRoomId, int userId);

	String getUsernameById(int Id);

	List<DmListDto> findDmRoom(int userId);
	
	List<DmRoom> findDmRoomById(int userId);

	List<Map<String, Object>> findMemberId(int dmRoomId);

	Dm findMyNewDm(int userId);

}
