package com.yangworld.app.domain.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yangworld.app.domain.profile.dto.ProfileDto;
import com.yangworld.app.domain.profile.entity.Profile;
import com.yangworld.app.domain.profile.repository.ProfileRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	

	@Override
	public int insertProfile(ProfileDto profile) {
		return profileRepository.insertProfile(profile);
	}
}
