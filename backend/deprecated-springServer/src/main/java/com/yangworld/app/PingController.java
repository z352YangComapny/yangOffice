package com.yangworld.app;

import com.yangworld.app.config.auth.PrincipalDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class PingController {
	@GetMapping("")
	public String index(){
		return "forward:/index.jsp";
	}

	@GetMapping("/ping")
	public ResponseEntity<String> pingcontroller() {
		return ResponseEntity.ok("pong");
	}

	@GetMapping("/user")
	public ResponseEntity<Object> userAuthTestController(Authentication authentication) {
		//로그인한 맴버 정보 꺼내쓰는법
		log.info("memberId={}",((PrincipalDetails)authentication.getPrincipal()).getId());
		return ResponseEntity.ok(authentication.getPrincipal());
	}

	@GetMapping("/admin")
	public ResponseEntity<Object> adminAuthTestController(Authentication authentication) {
		return ResponseEntity.ok(authentication.getPrincipal());
	}

}
