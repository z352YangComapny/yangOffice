package com.yangworld.app.domain.world.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangworld.app.domain.world.repository.WorldRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WorldServiceImpl {

	@Autowired
	private WorldRepository worldRepository;
	
}
