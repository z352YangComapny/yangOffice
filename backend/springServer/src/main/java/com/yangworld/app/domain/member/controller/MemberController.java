package com.yangworld.app.domain.member.controller;


import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yangworld.app.commons.MailSender;
import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.config.auth.PrincipalDetailsService;
import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.member.dto.FollowDto;
import com.yangworld.app.domain.member.dto.SignUpDto;
import com.yangworld.app.domain.member.dto.UpdateDto;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.service.MemberService;
import com.yangworld.app.domain.photoFeed.dto.PhotoAttachmentFeedDto;
import com.yangworld.app.domain.photoFeed.service.PhotoFeedService;
import com.yangworld.app.domain.profile.entity.ProfileDetails;
import com.yangworld.app.domain.profile.entity.State;
import com.yangworld.app.domain.profile.service.ProfileService;

import lombok.extern.slf4j.Slf4j;

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
    private ProfileService profileService;
    
    @Autowired
    private PhotoFeedService photoFeedService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailSender;

    @GetMapping("/memberLogin.do")
    public void memberLogin() {
    }

    @GetMapping("/memberCreate.do")
    public void memberCreate() {
    }

    @GetMapping("/userPage")
    public String userHome(@AuthenticationPrincipal PrincipalDetails principal) {
        return "redirect:/member/userPage/" + principal.getId();
    }

    @GetMapping("/userPage/{id}")
    public String userPage(@PathVariable("id") int id, Model model) {
        Member member = memberService.findById(id);
        log.info("member@Home={}", member);
        model.addAttribute("member", member);
        // 프로필 정보 가져오기
        ProfileDetails profile = profileService.getProfileByMemberId(id);
        log.info("profile={}", profile);
		
		List<PhotoAttachmentFeedDto> photoList = photoFeedService.selectFeed(id); 
		
        if(profile !=null){
            // 프로필 사진 가져오기
            List<Attachment> profileAttachments = profileService.getAttachmentsByProfileId(profile.getId());
            log.info("profileAttachments={}", profileAttachments);
            model.addAttribute("profile", profile);
            model.addAttribute("profileAttachments", profileAttachments);
            model.addAttribute("principalBday", member.getBirthday());
            model.addAttribute("principalName", member.getName());
            log.info("profile = {}", profile);
            log.info("profileAttachment = {}", profileAttachments);
        } else{
            ProfileDetails.builder()
                    .attachments(null)
                    .state(State.A)
                    .id(0).build();
        }

		log.info("photoList={}", photoList);
	    model.addAttribute("photoList", photoList);
	    

        return "member/userPage";
    }


    @PostMapping("/memberCreate.do")
    public String memberCreate(@Valid SignUpDto signUpDto, BindingResult bindingResult, RedirectAttributes redirectAttr) {
        log.info("signUp info = {}", signUpDto);

        if (bindingResult.hasErrors()) {
            ObjectError error = bindingResult.getAllErrors().get(0);
            redirectAttr.addFlashAttribute("msg", error.getDefaultMessage());
            return "redirect:/member/memberCreate.do";
        }
        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        log.info("password={}", passwordEncoder.encode(signUpDto.getPassword()));
        memberService.insertMember(signUpDto);
        redirectAttr.addFlashAttribute("msg", "🌷회원가입을 축하드립니다🌷");
        return "profile/profileCreate";
    }

    @PostMapping("/memberUpdate.do")
    public ResponseEntity<?> memberUpdate(@AuthenticationPrincipal PrincipalDetails principal,
                                          @RequestBody UpdateDto updateDto) {

        log.info("updateDto={}", updateDto);
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

        Member member = (PrincipalDetails) newAuthentication.getPrincipal();

        return ResponseEntity.ok().body(Map.of("msg", "회원정보가 수정되었습니다.", "member", member));
    }


    @GetMapping("/checkIdDuplicate.do")
    public ResponseEntity<?> checkIdDuplicate(@RequestParam String username) {
        boolean available = false;
        try {
            UserDetails principal = principalDetailsService.loadUserByUsername(username);

        } catch (UsernameNotFoundException e) {
            available = true; // 찾았는데 없으면 오류가 발생하고, 해당 Id는 사용이 가능해짐
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("available", available, "chkusername", username));

    }

    @GetMapping("/checkNicknameDuplicate.do")
    public ResponseEntity<?> checkNicknameDuplicate(@RequestParam String nickname) {
        boolean available = false;
        Member member = memberService.findByNickname(nickname);
        if (member == null) {
            available = true;
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("available", available));
    }

    /**
     * 휴대전화 중복검사
     *
     * @param phone
     * @return
     */
    @GetMapping("/checkPhoneDuplicate.do")
    public ResponseEntity<?> checkPhoneDuplicate(@RequestParam String phone) {
        boolean available = false;
        Member member = memberService.findByPhone(phone);
        if (member == null) {
            available = true;
        }
        return ResponseEntity.ok().body(Map.of("available", available));
    }

    // 회원가입시 이메일 인증요청
    @GetMapping("/checkEmail.do")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        log.info("email={}", email);

        return ResponseEntity.ok().body(Map.of("emailAuth", mailSender.joinEmail(email)));
    }

    //아이디찾기 시 요청
    @PostMapping("/checkEmailSearch.do")
    public ResponseEntity<?> checkEmailSearch(@RequestParam String email) {
        log.info("email={}", email);
        Member member = memberService.findMemberByEmail(email);
        String username = "";
        if (member == null) {
            username = "회원정보를 찾을 수 없습니다. 가입하신 이메일을 다시 확인해주세요.";
        } else {
            username = member.getUsername();
            log.info("username = {}", username);
        }

        return ResponseEntity.ok().body(Map.of("emailAuth", mailSender.joinEmail(email), "username", username));
    }

    //비밀번호 재설정
    @PostMapping("/resetPassword.do")
    public ResponseEntity<?> resetPassword(@RequestParam String password, @RequestParam String username) {

        log.info("password ={}", password);
        log.info("username={}", username);
        String newPassword = passwordEncoder.encode(password);
        log.info("newPwd={}", newPassword);

        int result = memberService.resetPassword(newPassword, username);
        log.info("result@reset = {}", result);

        return ResponseEntity.ok().body(Map.of("msg", "비밀번호 재설정 완료"));


    }


    // 회원삭제
    @PostMapping("/delete")
    public ResponseEntity<?> delete(@AuthenticationPrincipal PrincipalDetails principal) {

        log.info("princial ={}", principal);
        memberService.deleteMember(principal.getUsername());

        return ResponseEntity.ok().build();

    }

    //follow 하기
    @PostMapping("/follow")
    public ResponseEntity<?> follow(@AuthenticationPrincipal PrincipalDetails principal,
                                    @RequestBody FollowDto followDto) {
        log.info("followDto = {}", followDto);
        followDto.setFollower(principal.getId());
        log.info("followDto={}", followDto);

        memberService.insertFollowee(followDto);

        return ResponseEntity.ok().build();
    }

    //unfollow 하기
    @PostMapping("/unfollow")
    public ResponseEntity<?> unfollow(@AuthenticationPrincipal PrincipalDetails principal,
                                      @RequestBody FollowDto unfollow) {
        unfollow.setFollower(principal.getId());
        memberService.deleteFollowee(unfollow);

        return ResponseEntity.ok().build();
    }

    //member 상세정보
    @GetMapping("/memberDetail.do")
    public void memberDetail(@AuthenticationPrincipal PrincipalDetails principal, Authentication authentication) {

        principal = (PrincipalDetails) authentication.getPrincipal();
        Object credentials = authentication.getCredentials();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

    }

    @GetMapping("/memberList")
    public ResponseEntity<?> memberList(@RequestParam String inputText, @RequestParam(defaultValue="1") int page,
                                        @RequestParam int limit,
                                        @AuthenticationPrincipal PrincipalDetails principal) {
        //pagination
        Map<String, Object> params = Map.of("page", page, "limit", limit);
        List<Member> memberList = null;
        if (inputText.equals("")) {
            memberList = memberService.findAllMember();
           // log.info("memberList@no ={}", memberList);
        } else {
            memberList = memberService.findMemberByText(inputText);
           // log.info("memberList@input={}", memberList);
        }
        List<FollowDto> followList = memberService.findFollowee(principal.getId());
        //log.info("followeeList={}", followList);
        return ResponseEntity.ok().body(Map.of("memberList", memberList,"followList", followList));
    }
}