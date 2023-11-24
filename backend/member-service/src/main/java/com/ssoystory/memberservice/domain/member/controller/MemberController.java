package com.ssoystory.memberservice.domain.member.controller;

import com.ssoystory.memberservice.domain.member.dto.MemberUpdateDto;
import com.ssoystory.memberservice.domain.member.dto.SignInDto;
import com.ssoystory.memberservice.domain.member.dto.SignUpDto;
import com.ssoystory.memberservice.domain.member.entity.Member;
import com.ssoystory.memberservice.domain.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/member")
@Slf4j
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/ping")
    public ResponseEntity<String> pong(){
        return ResponseEntity.ok("PONG");
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto){
        try {
            memberService.signup(signUpDto);
        } catch (RuntimeException e){
            log.info("Sign UP Error={}",e);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Member> findById(@PathVariable long id){
        log.info("Input Id@findById={}", id);
        Optional<Member> member = memberService.findById(id);
        if(member.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(member.get());
    }
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInDto signInDto){
        log.info("Dto = {}", signInDto);
        String accessToken;
        accessToken = memberService.signIn(signInDto);
        if(accessToken == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().header("Authorization",accessToken).build();
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody MemberUpdateDto memberUpdateDto,
                                    @RequestHeader("Authorization") String jwtToken
    ){
        if (jwtToken==null){
            return ResponseEntity.badRequest().body("Can not find a AccessToken");
        }
        try {
            memberService.updateMember(memberUpdateDto, jwtToken);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Member>> findAll(){
        List<Member> members= memberService.findAll();
        return ResponseEntity.ok(members);
    }
}