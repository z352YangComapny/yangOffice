package com.yangworld.app.domain.guestbook.service;

import java.util.List;
import java.util.Map;

import com.yangworld.app.domain.guestbook.dto.GuestBookCreateDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookDeleteDto;
import com.yangworld.app.domain.guestbook.entity.GuestBook;

public interface GuestBookService {

	int insertGuestBook(GuestBookCreateDto guestBook);

	int deleteGuestBook(GuestBookDeleteDto delete);

	int updateGuestBook(GuestBook guestBook);

	List<GuestBook> findAll(Map<String, Object> params);

}
