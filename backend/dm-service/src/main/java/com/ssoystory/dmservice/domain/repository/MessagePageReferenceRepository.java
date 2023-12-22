package com.ssoystory.dmservice.domain.repository;

import com.ssoystory.dmservice.domain.entity.MessagePageReference;
import com.ssoystory.dmservice.domain.entity.MessagePageReferenceKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagePageReferenceRepository extends JpaRepository<MessagePageReference, MessagePageReferenceKey> {

}
