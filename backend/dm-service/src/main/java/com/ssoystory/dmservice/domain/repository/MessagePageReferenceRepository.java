package com.ssoystory.dmservice.domain.repository;

import com.ssoystory.dmservice.domain.entity.MessagePageReference;
import com.ssoystory.dmservice.domain.entity.MessagePageReferenceKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessagePageReferenceRepository extends JpaRepository<MessagePageReference, MessagePageReferenceKey> {
    @Query("SELECT mpr FROM MessagePageReference mpr " +
            "WHERE (mpr.id.participationId1 = :senderId AND mpr.id.participationId2 = :receiverId) " +
            "   OR (mpr.id.participationId1 = :receiverId AND mpr.id.participationId2 = :senderId)")
    Optional<MessagePageReference> findById(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);
}
