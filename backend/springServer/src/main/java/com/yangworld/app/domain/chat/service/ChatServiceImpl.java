package com.yangworld.app.domain.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangworld.app.domain.chat.dto.ChatListDto;
import com.yangworld.app.domain.chat.entity.Chat;
import com.yangworld.app.domain.chat.repository.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService{

	
	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public int sendChat(Chat chat) {
		int result = chatRepository.sendChat(chat);
		return result;
	}
	
	@Override
	public List<ChatListDto> findChatList() {
		return chatRepository.findChatList();
	}
	
	@Override
	public String findById(int memberId) {
		return chatRepository.findById(memberId);
	}
	
}
