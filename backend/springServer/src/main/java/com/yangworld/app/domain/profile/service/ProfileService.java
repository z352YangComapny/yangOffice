package com.yangworld.app.domain.profile.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.profile.dto.AdminProfileDto;
import com.yangworld.app.domain.profile.dto.ProfileAll;
import com.yangworld.app.domain.profile.entity.State;

public interface ProfileService {

    AdminProfileDto findProfileByMemberIdForAdmin(int id);

    List<ProfileAll> findProfileAll(String userName);

    int updateProfile(int profileId, State state, String introduction, PrincipalDetails member, List<MultipartFile> upFiles) throws IllegalStateException, IOException;

    int defaultUpdateProfile(PrincipalDetails member, String userName);

    int insertProfile(State state, String introduction, PrincipalDetails member, List<MultipartFile> upFiles) throws IllegalStateException, IOException;

    int defaultCreateProfile(PrincipalDetails member, String userName);
}


