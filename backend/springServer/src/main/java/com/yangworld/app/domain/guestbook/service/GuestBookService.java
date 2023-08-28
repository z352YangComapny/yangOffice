package com.yangworld.app.domain.guestbook.service;

import java.util.List;
import java.util.Map;

import com.yangworld.app.domain.guestbook.dto.GuestBookCreateDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookDeleteDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookUpdateDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookWithNicknameDto;

public interface GuestBookService {

	int insertGuestBook(GuestBookCreateDto guestBook);

	int deleteGuestBook(int deleteGuestbook);

	int updateGuestBook(GuestBookUpdateDto _guestBook);

	List<GuestBookWithNicknameDto> findAll(Map<String, Object> params);

	int countAllGuestbook(int memberId);

}
