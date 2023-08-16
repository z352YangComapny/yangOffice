package com.yangworld.app.domain.guestbook.service;

import java.util.List;
import java.util.Map;

import com.yangworld.app.domain.guestbook.entity.GuestBook;

public interface GuestBookService {

	int insertGuestBook(GuestBook guestBook);

	int deleteGuestBook(GuestBook guestBook);

	int updateGuestBook(GuestBook guestBook);

	List<GuestBook> findAll(Map<String, Object> params);

}
