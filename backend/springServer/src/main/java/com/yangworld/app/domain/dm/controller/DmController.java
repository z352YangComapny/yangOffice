package com.yangworld.app.domain.dm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yangworld.app.domain.dm.service.DmService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/dm")
public class DmController {
	
	@Autowired
	private DmService dmService;
}
