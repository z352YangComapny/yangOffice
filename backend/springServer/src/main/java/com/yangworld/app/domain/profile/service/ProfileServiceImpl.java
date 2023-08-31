package com.yangworld.app.domain.profile.service;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.attachment.entity.AttachmentProfile;
import com.yangworld.app.domain.attachment.repository.AttachmentRepository;
import com.yangworld.app.domain.member.entity.Member;
import com.yangworld.app.domain.member.repository.MemberRepository;
import com.yangworld.app.domain.profile.dto.AdminProfileDto;
import com.yangworld.app.domain.profile.entity.Profile;
import com.yangworld.app.domain.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MemberRepository memberRepository;


    @Override
    public AdminProfileDto findProfileByMemberIdForAdmin(int id) {
        Profile profile = profileRepository.findProfileByMemberIdForAdmin(id);
        AttachmentProfile attachment = attachmentRepository.findAttachmentByProfileId(profile.getId());
        Member member = memberRepository.findById(id);
        int followerCount = memberRepository.findFollowerCountByMemberId(id);
        int followeeCount = memberRepository.findFolloweeCountByMemberId(id);

//        private int id;
//        private int memberId;
//        private State state;
//        private String introduction;
//        private String renamedFilename;
//        private int follower;
//        private int followee;

        AdminProfileDto adminProfileDto = AdminProfileDto.builder()
                .id(profile.getId())
                .username(member.getUsername())
                .state(profile.getState())
                .introduction(profile.getIntroduction())
                .renamedFilename(attachment.getRenamedFilename())
                .follower(followerCount)
                .followee(followeeCount)
                .build();
        log.info("{}",adminProfileDto);
        return adminProfileDto;
    }
    
    
    
    
    
    
    
    
    
}
