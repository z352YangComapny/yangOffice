package com.yangworld.app.domain.world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yangworld.app.domain.world.service.WorldService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WorldController {

	@Autowired
	private WorldService worldService;
	
	
}
