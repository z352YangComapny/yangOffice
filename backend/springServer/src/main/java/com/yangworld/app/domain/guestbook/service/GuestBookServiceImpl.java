package com.yangworld.app.domain.guestbook.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangworld.app.domain.guestbook.dto.GuestBookCreateDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookDeleteDto;
import com.yangworld.app.domain.guestbook.dto.GuestBookUpdateDto;
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
	public int deleteGuestBook(GuestBookDeleteDto delete) {
		return guestBookRepository.deleteGuestBook(delete);
	}

	@Override
	public int updateGuestBook(GuestBookUpdateDto _guestBook) {
		return guestBookRepository.updateGuestBook(_guestBook);
	}

	@Override
	public List<GuestBook> findAll(Map<String, Object> params) {
		return guestBookRepository.findAll(params);
	}

}
