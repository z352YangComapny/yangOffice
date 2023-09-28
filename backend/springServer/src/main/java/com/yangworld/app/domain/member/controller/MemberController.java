package com.yangworld.app.domain.member.controller;


import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.config.auth.PrincipalDetailsService;
import com.yangworld.app.domain.member.dto.*;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@RequestMapping("/member")
@Slf4j
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    PrincipalDetailsService principalDetailsService;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpDto signUpDto){
        log.info("signUp info = {}",signUpDto);

        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        memberService.insertMember(signUpDto);
        return ResponseEntity.ok().build();
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

        // 업데이트 한 회원의 새 정보를 authentication 에 새롭게 담아주기
        PrincipalDetails principalDetails = memberService.loadUserByUsername(principal.getUsername());
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                                        principalDetails,
                                        principalDetails.getPassword(),
                                        principalDetails.getAuthorities()
                                        );
        log.info("newAuthentication = {}", newAuthentication);
        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@AuthenticationPrincipal PrincipalDetails principal){

        log.info("princial ={}", principal);
        memberService.deleteMember(principal.getUsername());

        return ResponseEntity.ok().build();

    }

    @PostMapping("/follow")
    public ResponseEntity<?> follow(@AuthenticationPrincipal PrincipalDetails principal,
                                    @RequestParam String hostname){

        memberService.insertFollowee(principal, hostname);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/unfollow")
    public ResponseEntity<?> unfollow(@AuthenticationPrincipal PrincipalDetails principal,
                                      @RequestParam String hostname){

        memberService.deleteFollowee(principal, hostname);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/memberDetail")
    public ResponseEntity<Member> memberDetail(Authentication authentication) {
        Member member = (Member) authentication.getPrincipal();
        return ResponseEntity.ok(member);
    }
    
    @GetMapping("/memberCount")
    public ResponseEntity<?> memberCount(){

        List<MonthlyMemberCountDto> monthlyCountList = memberService.findMonthlyMemberCount();

        return ResponseEntity.ok(monthlyCountList);
    }

    @GetMapping("/deletedMemberCount")
    public ResponseEntity<?> deletedMemberCount(){

        List<MonthlyMemberCountDto> monthlyDeletedCountList = memberService.findMonthlyDeletedMemberCount();
        return ResponseEntity.ok(monthlyDeletedCountList);
    }

    @GetMapping("/OAuthMemberCount")
    public ResponseEntity<?> OAuthMemberCount(){
        List<OAuthMemberDto> oauthMemberCountList = memberService.findOAuthMemberCount();
        return ResponseEntity.ok(oauthMemberCountList);
    }

    @GetMapping("")
    public ResponseEntity<List<SearchMemberDto>> memberSearch(@RequestParam String keyword){
        List<SearchMemberDto> memberList = memberService.searchMember(keyword);
        return ResponseEntity.ok(memberList);
    }

    /*
    * 아이디 중복검사
    * */
    @GetMapping("/checkIdDuplicate")
    public ResponseEntity<?> checkIdDuplicate(@RequestParam String username){
        boolean available = false;
        try {
            UserDetails principal = principalDetailsService.loadUserByUsername(username);

        } catch (UsernameNotFoundException e) {
            available = true; // 찾았는데 없으면 오류가 발생하고, 해당 Id는 사용이 가능해짐
        }
        return ResponseEntity.ok(available);

    }

    /**
     * nickname 중복검사
     * */
    @GetMapping("/checkNicknameDuplicate")
    public ResponseEntity<?> checkNicknameDuplicate(@RequestParam String nickname){
        boolean available = false;
        Member member = memberService.findByNickname(nickname);
        if (member == null) {
            available = true;
        }
        return ResponseEntity.ok(available);

    }

    /**
     * 휴대전화 중복검사
     * */
    @GetMapping("/checkPhoneDuplicate")
    public ResponseEntity<?> checkPhoneDuplicate(@RequestParam String phone){
        boolean available = false;
        Member member = memberService.findByPhone(phone);
        if (member == null) {
            available = true;
        }
        return ResponseEntity.ok(available);
    }

 }


