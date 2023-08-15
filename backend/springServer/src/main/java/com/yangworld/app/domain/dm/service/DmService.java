package com.yangworld.app.domain.dm.service;

import java.util.List;
import java.util.Set;

import com.yangworld.app.domain.dm.dto.DmSendDto;
import com.yangworld.app.domain.dm.entity.Dm;

public interface DmService {

	int insertDm(Dm dm);

	List<Dm> findMyDm(int userId);

    void insertDmRoom(int participant1, int participant2);

	int deleteDmRoom(int participant1, int participant2);
}
