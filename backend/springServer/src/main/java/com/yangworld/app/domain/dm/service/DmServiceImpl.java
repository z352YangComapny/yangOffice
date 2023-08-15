package com.yangworld.app.domain.dm.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	@Override
	public int insertDm(Dm dm) {
		int result = dmRepository.insertDm(dm);
		return result;
	}
	
	@Override
	public List<Dm> findMyDm(int userId) {
		List<Dm> myDms = dmRepository.findMyDm(userId);
		return myDms;
	}

	@Override
	public void insertDmRoom(int participant1, int participant2) {
		dmRepository.insertDmRoom(participant1, participant2);
	}

	@Override
	public int deleteDmRoom(int participant1, int participant2) {
		return dmRepository.deleteDmRoom(participant1,participant2);
	}

}
