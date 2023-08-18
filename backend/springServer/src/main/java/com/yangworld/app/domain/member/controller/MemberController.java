package com.yangworld.app.domain.member.controller;


import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.config.auth.PrincipalDetailsService;
import com.yangworld.app.domain.member.dto.*;
import com.yangworld.app.domain.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import redis.clients.jedis.Response;

import javax.validation.Valid;
import java.util.Map;

@Validated
@Controller
@RequestMapping("/member")
@Slf4j
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private PrincipalDetailsService principalDetailsService;


    @Autowired
    PasswordEncoder passwordEncoder;
    
    @GetMapping("/memberLogin.do")
	public void memberLogin() {}

    @GetMapping("/memberCreate.do")
    public void memberCreate(){}

    @PostMapping("/memberCreate.do")
    public String memberCreate(@Valid SignUpDto signUpDto, BindingResult bindingResult, RedirectAttributes redirectAttr){
        log.info("signUp info = {}",signUpDto);

        if(bindingResult.hasErrors()){
            ObjectError error = bindingResult.getAllErrors().get(0);
            redirectAttr.addFlashAttribute("msg", error.getDefaultMessage());
            return "redirect:/member/memberCreate.do";
        }
        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        log.info("password={}", passwordEncoder.encode(signUpDto.getPassword()));
        memberService.insertMember(signUpDto);
        return "redirect:/";
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@AuthenticationPrincipal PrincipalDetails principal,
                                    @RequestBody UpdateDto updateDto){

        log.info("modify dto = {}", updateDto);
        if(passwordEncoder.encode(updateDto.getPassword()).equals(principal.getPassword())){
            updateDto.setPassword(principal.getPassword());
        } else{
            updateDto.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        }
        // 로그인한 회원의 정보 업데이트
        memberService.updateMember(updateDto, principal.getUsername());

        // 업데이트 한 회원의 새 정보를 authentication에 새롭게 담아주기
        PrincipalDetails principalDetails = (PrincipalDetails) principalDetailsService.loadUserByUsername(principal.getUsername());
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                                        principalDetails,
                                        principalDetails.getPassword(),
                                        principalDetails.getAuthorities()
                                        );
        log.info("newAuthentication = {}", newAuthentication);
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@AuthenticationPrincipal PrincipalDetails principal){

        log.info("princial ={}", principal);
        memberService.deleteMember(principal.getUsername());

        return ResponseEntity.ok().build();

    }

    @PostMapping("/follow")
    public ResponseEntity<?> follow(@AuthenticationPrincipal PrincipalDetails principal,
                                    @RequestBody FollowDto followDto){
        log.info("followDto = {}", followDto);
        followDto.setFollower(principal.getId());
        log.info("followDto={}", followDto);

        memberService.insertFollowee(followDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/unfollow")
    public ResponseEntity<?> unfollow(@AuthenticationPrincipal PrincipalDetails principal,
                                      @RequestBody FollowDto unfollow){
        unfollow.setFollower(principal.getId());
        memberService.deleteFollowee(unfollow);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/findId")
    public ResponseEntity<?> findId(@RequestBody FindIdDto findIdDto){

        String username = memberService.findMemberByEmail(findIdDto);
        if(username == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("msg", "일치하는 이메일 주소가 없습니다. 확인 바랍니다."));
        } else{
            log.info("username = {}", username);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("username", username));
        }

    }

 }








