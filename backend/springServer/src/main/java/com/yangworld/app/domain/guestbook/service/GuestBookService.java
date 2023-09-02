package com.yangworld.app.domain.guestbook.service;

import com.yangworld.app.config.auth.PrincipalDetails;
import com.yangworld.app.domain.guestbook.dto.GuestBookCreateDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookWithNicknameDto;
import com.yangworld.app.domain.guestbook.dto.GuestbookAdminDto;
import com.yangworld.app.domain.guestbook.dto.GuestbookDailyDto;
import com.yangworld.app.domain.guestbook.entity.GuestBook;

import java.util.List;
import java.util.Map;

public interface GuestBookService {

	int insertGuestBook(GuestBookCreateDto guestBook, PrincipalDetails member);

	int deleteGuestBook(GuestBook guestBook);

	int updateGuestBook(GuestBook guestBook);

    int guestbookTotalNo();

	List<GuestbookAdminDto> guestbookList(int pageNo, int pageSize);

    GuestBook findGuestBookById(int guestBookId);

	List<GuestbookDailyDto> findGuestBookDaily();

	List<GuestBookWithNicknameDto> findAll(Map<String, Object> params, String memberId);

	int countAllGuestBook(String hostname);
}
