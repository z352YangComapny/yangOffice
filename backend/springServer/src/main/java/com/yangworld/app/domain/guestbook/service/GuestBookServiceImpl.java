package com.yangworld.app.domain.guestbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangworld.app.domain.guestbook.entity.GuestBook;
import com.yangworld.app.domain.guestbook.repository.GuestBookRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GuestBookServiceImpl implements GuestBookService{
	
	@Autowired
	private GuestBookRepository guestBookRepository;

	@Override
	public int insertGuestBook(GuestBook guestBook) {
		return guestBookRepository.insertGuestBook(guestBook);
	}

	@Override
	public int deleteGuestBook(GuestBook guestBook) {
		return guestBookRepository.deleteGuestBook(guestBook);
	}

	@Override
	public int updateGuestBook(GuestBook guestBook) {
		return guestBookRepository.updateGuestBook(guestBook);
	}

}
