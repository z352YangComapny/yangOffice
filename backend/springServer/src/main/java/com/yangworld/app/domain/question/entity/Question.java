package com.yangworld.app.domain.question.entity;

import java.time.LocalDateTime;

import com.yangworld.app.domain.dm.entity.Dm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
	
	private int id;
	private int writerId;
	private String title;
	private String content;
	private QuestionType type; // 문의 Q , 공지사항 N
	private LocalDateTime regDate;

}
