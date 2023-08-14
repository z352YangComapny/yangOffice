package com.yangworld.app.domain.dm.service;

import java.util.List;

import com.yangworld.app.domain.dm.dto.DmSendDto;
import com.yangworld.app.domain.dm.entity.Dm;

public interface DmService {

	int insertDm(Dm dm);

	List<Dm> findDmById(int senderId);


}
