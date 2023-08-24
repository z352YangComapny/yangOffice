package com.yangworld.app.domain.chat.service;

import java.util.List;

import com.yangworld.app.domain.chat.entity.Chat;

public interface ChatService  {

	int sendChat(Chat chat);

	List<Chat> findChatList();
}
