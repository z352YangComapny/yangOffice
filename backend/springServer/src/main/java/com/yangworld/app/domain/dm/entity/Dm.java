package com.yangworld.app.domain.dm.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dm {
	private int id;
	private int receiverId;
	private int senderId;
	private LocalDateTime regDate;

}
