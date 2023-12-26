package com.ssoystory.memberservice.domain.profile.repository;

import com.ssoystory.memberservice.domain.profile.entity.Profile;
import com.ssoystory.memberservice.domain.profile.entity.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, ProfileId> {
    Optional<Profile> findProfileByIdProfileId(Long profileId);
    Optional<Profile> findProfileByIdMemberId(Long memberId);

    @Query("SELECT p " +
            "FROM Profile p " +
            "JOIN Follow f " +
            "ON f.id.followerId = p.id.memberId " +
            "AND p.id.profileId = (SELECT MAX(p2.id.profileId) FROM Profile p2 WHERE p2.id.memberId = f.id.followerId) " +
            "WHERE f.id.followerId = :userId")
    List<Profile> getFollowerList(@Param("userId") Long userId);

    @Query("SELECT p " +
            "FROM Profile p " +
            "JOIN Follow f " +
            "ON f.id.followeeId = p.id.memberId " +
            "AND p.id.profileId = (SELECT MAX(p2.id.profileId) FROM Profile p2 WHERE p2.id.memberId = f.id.followeeId) " +
            "WHERE f.id.followeeId = :userId")
    List<Profile> getFolloweeList(@Param("userId") Long userId);
}
