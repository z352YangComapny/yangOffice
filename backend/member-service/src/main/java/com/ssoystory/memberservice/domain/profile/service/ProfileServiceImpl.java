package com.ssoystory.memberservice.domain.profile.service;

import com.ssoystory.memberservice.common.s3.S3Service;
import com.ssoystory.memberservice.domain.member.entity.Member;
import com.ssoystory.memberservice.domain.member.repository.MemberRepository;
import com.ssoystory.memberservice.domain.profile.entity.Profile;
import com.ssoystory.memberservice.domain.profile.entity.ProfileId;
import com.ssoystory.memberservice.domain.profile.repository.ProfileRepository;
import com.ssoystory.memberservice.exception.MemberNotFoundException;
import com.ssoystory.memberservice.exception.ProfileNotFoundException;
import com.ssoystory.memberservice.exception.S3UploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private S3Service s3Service;
    @Override
    public Profile save(long id, String content, int state, MultipartFile file , String username) throws IOException, S3UploadException {
        String fileURL = s3Service.uploadFile(file, username);
        ProfileId profileId = ProfileId.builder()
                .memberId(id)
                .build();
        Profile profile = Profile.builder()
                .id(profileId)
                .content(content)
                .state(state)
                .profilePhotoURL(fileURL)
                .build();
       return profileRepository.save(profile);
    }

    @Override
    public Profile update(long id, String username, String content, int state, MultipartFile file, Long postId) throws ProfileNotFoundException, IOException {
        Optional<Profile> profile = profileRepository.findProfileByIdProfileId(postId);
        if(profile.isEmpty()) throw new ProfileNotFoundException();
        Profile existingProfile = profile.get();
        existingProfile.setState(state);
        if(!file.isEmpty()) {
            String profileUrl = s3Service.uploadFile(file, username);
            existingProfile.setProfilePhotoURL(profileUrl);
        }
        existingProfile.setContent(content);
        return profileRepository.save(existingProfile);
    }

    @Override
    public Profile findByUsername(String username) {
        Optional<Member> optionalMember = memberRepository.findMemberByUsername(username);
        if(optionalMember.isEmpty()) throw new MemberNotFoundException();
        Optional<Profile> profileOptional = profileRepository.findProfileByIdMemberId(optionalMember.get().getId());
        if(profileOptional.isEmpty()) throw new ProfileNotFoundException();
        return profileOptional.get();
    }
}
