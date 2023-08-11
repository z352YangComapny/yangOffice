package com.yangworld.app.domain.dm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yangworld.app.domain.dm.repository.DmRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class DmServiceImpl implements DmService {

	@Autowired
	private DmRepository dmReporitory;
}
