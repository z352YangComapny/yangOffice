package com.ssoystory.guestbookservice.domain.guestbook.repository;

import com.ssoystory.guestbookservice.domain.guestbook.entity.Guestbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestbookRepository extends JpaRepository<Guestbook,Long> {
    Page<Guestbook> findByOwnerId(Long ownerId, Pageable pageable);
}
