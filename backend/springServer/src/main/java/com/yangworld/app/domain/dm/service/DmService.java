package com.yangworld.app.domain.dm.service;

import java.util.List;
import java.util.Set;

import com.yangworld.app.domain.dm.dto.DmSendDto;
import com.yangworld.app.domain.dm.entity.Dm;

public interface DmService {

	int insertDm(Dm dm);

	List<Dm> findDmById(int senderId);

	Set<Integer> findMyDm(int receiverId);

	List<Dm> findAllDm();

	List<Dm> findDmDetails(int senderId, int receiverId);


    void insertDmRoom(int participant1, int participant2);
}
