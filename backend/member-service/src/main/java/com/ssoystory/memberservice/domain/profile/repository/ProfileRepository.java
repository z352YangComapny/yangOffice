package com.ssoystory.memberservice.domain.profile.repository;

import com.ssoystory.memberservice.domain.profile.entity.Profile;
import com.ssoystory.memberservice.domain.profile.entity.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, ProfileId> {
}
