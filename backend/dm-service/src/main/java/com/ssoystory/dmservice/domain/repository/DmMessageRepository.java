package com.ssoystory.dmservice.domain.repository;

import com.ssoystory.dmservice.domain.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DmMessageRepository extends JpaRepository<Message , Long> {
}