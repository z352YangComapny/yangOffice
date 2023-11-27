package com.ssoystory.memberservice.domain.profile.repository;

import com.ssoystory.memberservice.domain.profile.entity.Profile;
import com.ssoystory.memberservice.domain.profile.entity.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, ProfileId> {
    Optional<Profile> findProfileByIdProfileId(Long profileId);
    Optional<Profile> findProfileByIdMemberId(Long memberId);
}
