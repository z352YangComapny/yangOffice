package com.yangworld.app.domain.dm.service;

import java.util.List;
import java.util.Set;

import com.yangworld.app.domain.dm.dto.DmSendDto;
import com.yangworld.app.domain.dm.entity.Dm;
import com.yangworld.app.domain.dm.entity.DmRoom;

public interface DmService {

	int insertDm(Dm dm);

	List<Dm> findMyDmList(int userId);

    void insertDmRoom(int participant1, int participant2);

	int deleteDmRoom(int participant1, int participant2);

	List<Dm> findDmDetails(int dmRoomId);

	String getUsernameById(int Id);

	List<DmRoom> findDmRoom(int dmRoomId);
}
