package com.yangworld.app.domain.notification.service;

import com.yangworld.app.domain.chat.entity.Chat;
import com.yangworld.app.domain.dm.entity.Dm;

public interface NotificationService {

	int notifySendDm(Dm msg);

	
}
