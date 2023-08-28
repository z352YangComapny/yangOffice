package com.yangworld.app.oauth.naver.controller;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.config.auth.PrincipalDetailsService;
import com.yangworld.app.domain.member.dto.SignUpDto;
import com.yangworld.app.domain.member.service.MemberService;
import com.yangworld.app.oauth.naver.service.NaverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/oauth/naver")
@SessionAttributes({"access_token", "refresh_token"})
public class NaverController {

    @Autowired
    private NaverService naverService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PrincipalDetailsService principalService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public RedirectView login(){
        return new RedirectView(naverService.getAuthorizeUri());
   }

   @GetMapping("/callback")
    public RedirectView callback(@RequestParam String code, Model model, HttpServletRequest request){
        log.info("콜백 받았음");
        Map<String, Object> tokens = naverService.getTokens(code);

        log.info("tokens={}", tokens);

        // 세션에 토큰 저장
        model.addAttribute("access_toekn", tokens.get("access_token"));
        model.addAttribute("refresch_toekn", tokens.get("refresh_token"));



        // 사용자 정보요청
       Map<String, Object> attributes = naverService.getProfile((String)tokens.get("access_token"));
       log.info("attributes={}", attributes);

       Map<String, Object> response = (Map<String, Object>)attributes.get("response");

       // 회원가입/ 로그인 처리
       String username = response.get("id") + "@naver";
       PrincipalDetails member = null;

       try{
             member = (PrincipalDetails)principalService.loadUserByUsername(username);

           Authentication authentication = new UsernamePasswordAuthenticationToken(member, member.getPassword(), member.getAuthorities());
           SecurityContextHolder.getContext().setAuthentication(authentication);

           return new RedirectView(request.getContextPath()+"/member/userPage" + member.getId());

       } catch(UsernameNotFoundException ignore){


           log.info("response={}", response);

           String name = (String)response.get("name");
           String email = (String)response.get("email");
           String gender = (String)response.get("gender");
           String birthdate = (String)response.get("birthday");
           String birthyear = (String)response.get("birthyear");
           String _birthday = birthyear + "-" + birthdate;
           String nickname = (String)response.get("nickname");

           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           LocalDate birthday = LocalDate.parse(_birthday, formatter);

           String phone = ((String)response.get("mobile")).replace("-", "");

           SignUpDto signUpDto = SignUpDto.builder()
                   .username(username)
                   .password(passwordEncoder.encode("1234"))
                   .name(name)
                   .email(email)
                   .birthday(birthday)
                   .gender(gender)
                   .phone(phone)
                   .nickname(nickname)
                   .build();

           int result = memberService.insertMember(signUpDto);

           member = (PrincipalDetails)principalService.loadUserByUsername(username);


           Authentication authentication = new UsernamePasswordAuthenticationToken(member, member.getPassword(), member.getAuthorities());

           SecurityContextHolder.getContext().setAuthentication(authentication);

           // 프로필 설정창으로 이동하도록 변경예정
           return new RedirectView(request.getContextPath()+"/member/memberDetail.do");

       }

   }

}
