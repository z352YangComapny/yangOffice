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
	public List<Dm> findAllDm() {
		List<Dm> dms = dmRepository.findAllDm();
		return dms;
	}
	
	@Override
	public Set<Integer> findMyDm(int receiverId) {
		List<Dm> myDms = dmRepository.findMyDm(receiverId);
		Set<Integer> idSet = new HashSet<>();
		for(Dm dm : myDms) {
			idSet.add(dm.getSenderId());
		}
		return idSet;
	}
	
	@Override
	public List<Dm> findDmById(int senderId) {
		List<Dm> dmDetails = dmRepository.findDmById(senderId);
		return dmDetails;
	}
	
	@Override
	public List<Dm> findDmDetails(int senderId, int receiverId) {
		List<Dm> dmDetails = dmRepository.findDmDetails(senderId, receiverId);
		return dmDetails;
	}
	
}
