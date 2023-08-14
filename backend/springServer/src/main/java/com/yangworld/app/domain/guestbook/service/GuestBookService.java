package com.yangworld.app.domain.guestbook.service;

import com.yangworld.app.domain.guestbook.entity.GuestBook;

public interface GuestBookService {

	int insertGuestBook(GuestBook guestBook);

	int deleteGuestBook(GuestBook guestBook);

}
