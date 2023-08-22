package com.yangworld.app.oauth.controller;

import java.time.LocalDate;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.config.auth.PrincipalDetailsService;
import com.yangworld.app.domain.member.dto.SignUpDto;
import com.yangworld.app.oauth.service.KakaoService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequestMapping("/oauth/kakao")
@SessionAttributes({"access_token","refresh_token"})
public class KakaoController{

	@Autowired
	private KakaoService kakaoService;
	@Autowired
	private PrincipalDetailsService principalDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/login.do")
	public RedirectView login() {
		log.info("init={}");
		return new RedirectView(kakaoService.getAuthorizeUri());
	}
	
	@GetMapping("/callback.do")
	public RedirectView callback(
			@RequestParam String code,
			Model model,
			HttpServletRequest request
			){
		// 1. 토큰 발급
		Map<String, Object> tokens = kakaoService.getTokens(code);
		log.info("tokens={}",tokens);
		// 세션에 토큰 저장
		model.addAttribute("access_token",tokens.get("access_token"));
		model.addAttribute("refresh_token",tokens.get("refresh_token"));
	
		// 2. 사용자 정보 요청
		Map<String, Object> attributes = kakaoService.getProfile((String)tokens.get("access_token"));
		
		// 3. 회원가입+로그인처리 
		// 회원 조회 후 가입 여부 확인
		String memberId = attributes.get("id")+"@kakao";
		PrincipalDetails member = null;
		try {
			member = (PrincipalDetails) principalDetailsService.loadUserByUsername(memberId);
		}catch(UsernameNotFoundException ignore) {
			// 회원이 아닌 경우 
			Map<String, Object> kakaoAccount = (Map<String,Object>) attributes.get("kakao_account");
			Map<String, Object> profile = (Map<String,Object>) kakaoAccount.get("profile");
			log.info("profile={}",profile);
			String nickname = (String) profile.get("nickname");
			String email = (String) kakaoAccount.get("email");
			
			SignUpDto signUpDto = SignUpDto.builder()
								.username(memberId)
								.password(passwordEncoder.encode("1234"))
								.name("홍")
								.nickname(nickname)
								.gender("F")
								.phone("010-1234-1234")
								.email(email)
								.birthday(LocalDate.parse("1999-09-09"))
								.build();
			
			int result = principalDetailsService.insertMember(signUpDto);
			
			member = (PrincipalDetails) principalDetailsService.loadUserByUsername(memberId);
			
		}
		
		// 로그인 처리
		Authentication authentication = new UsernamePasswordAuthenticationToken(
					member,
					member.getPassword(),
					member.getAuthorities()
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new RedirectView(request.getContextPath()+"/");
	}

	
}
