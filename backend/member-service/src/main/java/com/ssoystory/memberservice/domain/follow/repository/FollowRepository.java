package com.ssoystory.memberservice.domain.follow.repository;

import com.ssoystory.memberservice.domain.follow.entity.Follow;
import com.ssoystory.memberservice.domain.follow.entity.FollowKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follow , FollowKey> {
}
