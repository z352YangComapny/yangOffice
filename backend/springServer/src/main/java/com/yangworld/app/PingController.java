package com.yangworld.app;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PingController {
	@GetMapping("/ping")
	public ResponseEntity<String> pingcontroller() {
		return ResponseEntity.ok("pong");
	}

	@GetMapping("/user")
	public ResponseEntity<Object> userAuthTestController(Authentication authentication) {
		return ResponseEntity.ok(authentication.getPrincipal());
	}
	@GetMapping("/admin")
	public ResponseEntity<Object> adminAuthTestController(Authentication authentication) {
		return ResponseEntity.ok(authentication.getPrincipal());
	}

}
