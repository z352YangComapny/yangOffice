package com.yangworld.app.domain.guestbook.service;

import com.yangworld.app.domain.guestbook.dto.GuestbookAdminDto;
import com.yangworld.app.domain.member.repository.MemberRepository;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangworld.app.domain.guestbook.entity.GuestBook;
import com.yangworld.app.domain.guestbook.repository.GuestBookRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GuestBookServiceImpl implements GuestBookService{

	@Autowired
	private GuestBookRepository guestBookRepository;
	@Autowired
	private MemberRepository memberRepository;

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

	@Override
	public int guestbookTotalNo() {
		return guestBookRepository.guestbookTotalNo();
	}

	@Override
	public List<GuestbookAdminDto> guestbookList(int pageNo, int pageSize) {
		int offset = (pageNo-1)*pageSize;
		RowBounds rowBounds = new RowBounds(offset, pageSize);
		List<GuestBook> guestBooks = guestBookRepository.guestBookList(rowBounds);
		List<GuestbookAdminDto> guestbookAdminDtos = new ArrayList<>();
		for(GuestBook guestBook : guestBooks){
			String writer = memberRepository.findById(guestBook.getWriterId()).getUsername();
			String toMember = memberRepository.findById(guestBook.getMemberId()).getUsername();
			GuestbookAdminDto guestbookAdminDto = GuestbookAdminDto.builder()
					.id(guestBook.getId())
					.writer(writer)
					.toMember(toMember)
					.content(guestBook.getContent())
					.regDate(guestBook.getRegDate())
					.build();
			guestbookAdminDtos.add(guestbookAdminDto);
		}
		return guestbookAdminDtos;
	}


}
