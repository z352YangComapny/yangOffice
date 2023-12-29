package com.ssoystory.memberservice.domain.member.repository;

import com.ssoystory.memberservice.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findMemberByUsername(String username);

    Optional<Member> findMemberByNickname(String nickname);
}
