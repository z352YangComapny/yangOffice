package com.yangworld.app.domain.question.dto;

import javax.validation.constraints.NotBlank;

import com.yangworld.app.domain.question.entity.Question;

import lombok.Data;

@Data
public class QuestionCreateQnaDto {
	
	private int id;
	@NotBlank(message ="문의제목 작성해주세요.")
	private String title;
	@NotBlank(message ="문의글 작성해주세요.")
	private String content;

	public Question toQna() {
		return Question.builder()
				.title(title)
				.content(content)
				.build();
	}	
	
	
}
