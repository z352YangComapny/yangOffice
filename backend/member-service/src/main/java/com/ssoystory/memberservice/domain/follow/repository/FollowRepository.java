package com.ssoystory.memberservice.domain.follow.repository;

import com.ssoystory.memberservice.domain.follow.entity.Follow;
import com.ssoystory.memberservice.domain.follow.entity.FollowKey;
import com.ssoystory.memberservice.domain.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow , FollowKey> {
    @Query("select f.id.followeeId from Follow f where f.id.followerId = :followerId ")
    List<Long> getFolloweeIdListByFollowerId(@Param("followerId") Long followerId);
}
