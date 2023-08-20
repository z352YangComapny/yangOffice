package com.yangworld.app.domain.profile.service;

import com.yangworld.app.domain.profile.dto.AdminProfileDto;

public interface ProfileService {

    AdminProfileDto findProfileByMemberIdForAdmin(int id);
}
