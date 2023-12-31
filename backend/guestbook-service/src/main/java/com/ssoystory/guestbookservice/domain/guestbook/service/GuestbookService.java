package com.ssoystory.guestbookservice.domain.guestbook.service;

import com.ssoystory.guestbookservice.domain.guestbook.dto.DeleteGuestbookDto;
import com.ssoystory.guestbookservice.domain.guestbook.dto.GuestbookDto;
import com.ssoystory.guestbookservice.domain.guestbook.entity.Guestbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GuestbookService {
    void save(Long authId, GuestbookDto guestbookDto) throws InterruptedException;

    Page<Guestbook> findById(String username, Pageable pageable) throws InterruptedException;

    void delete(Long authId, DeleteGuestbookDto guestbookDto);
}
