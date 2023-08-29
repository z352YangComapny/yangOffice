package com.yangworld.app.domain.guestbook.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangworld.app.domain.guestbook.dto.GuestBookCreateDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookDeleteDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookUpdateDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookWithNicknameDto;
import com.yangworld.app.domain.guestbook.entity.GuestBook;
import com.yangworld.app.domain.guestbook.repository.GuestBookRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GuestBookServiceImpl implements GuestBookService{
	
	@Autowired
	private GuestBookRepository guestBookRepository;

	@Override
	public int insertGuestBook(GuestBookCreateDto guestBook) {
		return guestBookRepository.insertGuestBook(guestBook);
	}

	@Override
	public int deleteGuestBook(int deleteGuestbook) {
		return guestBookRepository.deleteGuestBook(deleteGuestbook);
	}

	@Override
	public int updateGuestBook(GuestBookUpdateDto _guestBook) {
		return guestBookRepository.updateGuestBook(_guestBook);
	}

	@Override
	public List<GuestBookWithNicknameDto> findAll(Map<String, Object> params) {
		int memberId = (int) params.get("id");
		int page = (int) params.get("page");
		int limit = (int) params.get("limit");
		int offset = (page-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		return guestBookRepository.findAll(rowBounds, memberId);
	}

	@Override
	public int countAllGuestbook(int memberId) {
		return guestBookRepository.countAllGuestbook(memberId);
	}

	@Override
	public List<GuestBookWithNicknameDto> findReportedId(int id) {
		return guestBookRepository.findReportedId(id);
	}


}
