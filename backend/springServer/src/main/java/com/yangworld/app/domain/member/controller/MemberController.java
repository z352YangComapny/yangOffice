package com.yangworld.app.domain.member.controller;



import com.yangworld.app.commons.MailSender;
import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.config.auth.PrincipalDetailsService;
import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.member.dto.*;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.entity.MemberDetails;
import com.yangworld.app.domain.member.service.MemberService;
import com.yangworld.app.domain.photoFeed.dto.PhotoAttachmentFeedDto;
import com.yangworld.app.domain.photoFeed.service.PhotoFeedService;
import com.yangworld.app.domain.profile.entity.ProfileDetails;
import com.yangworld.app.domain.profile.entity.State;
import com.yangworld.app.domain.profile.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;

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
    private PhotoFeedService photoFeedService; 
    
    @Autowired
    private ProfileService profileService;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private MailSender mailSender;

    @GetMapping("/memberLogin.do")
    public void memberLogin() {}

    @GetMapping("/memberCreate.do")
    public void memberCreate() {}

    @GetMapping("/userPage")
    public String userHome(@AuthenticationPrincipal PrincipalDetails principal) {
        return "redirect:/member/userPage/" + principal.getId();
    }

    @GetMapping("/userPage/{id}")
    public String userPage(
		@AuthenticationPrincipal PrincipalDetails principal,
		@PathVariable("id") int id, Model model) {
        Member member = memberService.findById(id);
        log.info("member@Home={}", member);
        log.info("업데에에엣password={}", passwordEncoder.encode(member.getPassword()));
        model.addAttribute("member", member);
        // 프로필 정보 가져오기
        ProfileDetails profile = profileService.getProfileByMemberId(id);
        log.info("profile={}", profile);

		List<PhotoAttachmentFeedDto> photoList = photoFeedService.selectFeed(id);
        List<Attachment> profileAttachments =null;
        if(profile !=null){
            // 프로필 사진 가져오기
            profileAttachments = profileService.getAttachmentsByProfileId(profile.getId());
            model.addAttribute("id",id);
            
    	    model.addAttribute("photoList", photoList);
            log.info("profileAttachments={}", profileAttachments);
            model.addAttribute("profile", profile);
            model.addAttribute("profileAttachments", profileAttachments);
            model.addAttribute("principalBday", member.getBirthday());
            model.addAttribute("principalName", member.getName());
            model.addAttribute("PrincipalDetails", principal);
            log.info("profile = {}", profile);
            log.info("profileAttachment = {}", profileAttachments);
        } else{
            profile = ProfileDetails.builder()
                    .attachments(null)
                    .state(State.A)
                    .introduction("새롭게 작성해주세요")
                    .build();
        }
       // log.info("profileAttachment={}", profileAttachments);
        model.addAttribute("profileAttachments", profileAttachments);
        model.addAttribute("profile", profile);
        model.addAttribute("principalBday", member.getBirthday());
        model.addAttribute("principalName", member.getName());
        model.addAttribute("PrincipalDetails", principal);
	    model.addAttribute("photoList", photoList);
	    model.addAttribute("id",id);
	    
        return "member/userPage";
    }

    @PostMapping("/memberCreate.do")
    public String memberCreate(@Valid SignUpDto signUpDto, BindingResult bindingResult, RedirectAttributes redirectAttr
                               , Model model
        ) {
        log.info("signUp info = {}", signUpDto);

        if (bindingResult.hasErrors()) {
            ObjectError error = bindingResult.getAllErrors().get(0);
            redirectAttr.addFlashAttribute("msg", error.getDefaultMessage());
            return "redirect:/member/memberCreate.do";
        }
        signUpDto.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        log.info("password={}", passwordEncoder.encode(signUpDto.getPassword()));
        memberService.insertMember(signUpDto);
        //redirectAttr.addFlashAttribute("msg", "🌷회원가입을 축하드립니다🌷");
        model.addAttribute("member", signUpDto);
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

    @GetMapping({"/checkNicknameDuplicate.do", "/checkNicknameDuplicate2.do"})
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
    @GetMapping({"/checkPhoneDuplicate.do", "/checkPhoneDuplicate2.do"})
    public ResponseEntity<?> checkPhoneDuplicate(@RequestParam String phone) {
        boolean available = false;
        Member member = memberService.findByPhone(phone);
        if (member == null) {
            available = true;
        }
        return ResponseEntity.ok().body(Map.of("available", available));
    }

    // 회원가입시 이메일 인증요청
    @GetMapping({"/checkEmail.do", "/checkEmail2.do"})
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

    @PostMapping("/resetPassword2.do")
    public ResponseEntity<?> resetPassword2(@RequestParam String password, @RequestParam String username) {

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

        return ResponseEntity.ok().body(Map.of("msg", "탈퇴처리가 완료되었습니다. 이용해주셔서 감사합니다."));

    }

    //follow 하기
    @PostMapping("/addFollowee")
    public ResponseEntity<?>addFollowee(@AuthenticationPrincipal PrincipalDetails principal,
                                    @RequestParam String memberId) {

        Member member = memberService.findMemberbyUsername(memberId);

        FollowDto followDto = new FollowDto();
        followDto.setFollower(principal.getId());
        followDto.setFollowee(member.getId());
        log.info("followDto = {}", followDto);


        memberService.insertFollowee(followDto);

        return ResponseEntity.ok().body(Map.of("msg", "follow 완료 되었습니다."));
    }

    //unfollow 하기
    @PostMapping("/unfollow")
    public ResponseEntity<?> unfollow(@AuthenticationPrincipal PrincipalDetails principal,
                                      @RequestParam String memberId) {

        Member member = memberService.findMemberbyUsername(memberId);

        FollowDto unfollowDto = new FollowDto();
        unfollowDto.setFollower(principal.getId());
        unfollowDto.setFollowee(member.getId());
        log.info("followDto = {}", unfollowDto);

        memberService.deleteFollowee(unfollowDto);

        return ResponseEntity.ok().body(Map.of("msg", "unfollow 완료 되었습니다."));
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
        List<Member>  memberList = null;
        int totalMemberCount = 0;
        if (inputText.equals("")) {
            memberList = memberService.findAllMember(params);
            totalMemberCount = memberService.findTotalMemberCount();
           // log.info("memberList@no ={}", memberList);
            log.info("totalMemberCount={}", totalMemberCount);
        } else {
            memberList = memberService.findMemberByText(inputText, params);
            totalMemberCount = memberService.findTotalMemberCountByInput(inputText);
            log.info("totalMember@input={}", totalMemberCount);
           // log.info("memberList@input={}", memberList);
        }
        List<FollowDto> followList = memberService.findFollowee(principal.getId());
        //log.info("followeeList={}", followList);
        int principalId = principal.getId();
        log.info("limit={}", limit);
        int totalPages = (int)Math.ceil((double)totalMemberCount/limit);
        log.info("totalPages={}", totalPages);
        return ResponseEntity.ok().body(Map.of("memberList", memberList,"followList", followList, "principal", principalId, "totalPages", totalPages));
    }
}