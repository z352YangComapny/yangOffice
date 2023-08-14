package com.yangworld.app.domain.dm.service;

import java.util.List;

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
	public List<Dm> findDmById(int senderId) {
		List<Dm> dms = dmRepository.findDmById(senderId);
		return dms;
	}
	
}
