package com.yangworld.app.domain.profile.service;

import java.util.List;

import com.yangworld.app.domain.profile.dto.AdminProfileDto;
import com.yangworld.app.domain.profile.dto.ProfileAll;

public interface ProfileService {

    AdminProfileDto findProfileByMemberIdForAdmin(int id);

	List<ProfileAll> findProfileAll(String userName);
}
