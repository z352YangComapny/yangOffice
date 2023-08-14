package com.yangworld.app.domain.dm.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DmRoom {
	private int id;
	private List<Integer> participants;
	private LocalDateTime regDate;
}
