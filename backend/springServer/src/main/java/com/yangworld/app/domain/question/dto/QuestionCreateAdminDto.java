package com.yangworld.app.domain.question.dto;

import javax.validation.constraints.NotBlank;

import com.yangworld.app.domain.question.entity.Question;
import com.yangworld.app.domain.question.entity.QuestionType;

import lombok.Data;

@Data
public class QuestionCreateAdminDto {


	private int id;
	@NotBlank(message ="공지사항 제목 작성해주세요.")
	private String title;
	@NotBlank(message ="공지글 작성해주세요.")
	private String content;

	private QuestionType questionType;
	public Question toQna() {
		return Question.builder()
				.title(title)
				.content(content)
				.type(questionType)
				.build();
	}	
}
