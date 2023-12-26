package com.yangworld.app.domain.question.dto;

import javax.validation.constraints.NotBlank;

import com.yangworld.app.domain.question.entity.Question;

import lombok.Data;

@Data
public class QuestionUpdateQnaDto {
	@NotBlank(message = "수정할 문의사항 번호 입력")
	private Integer id;
	@NotBlank(message ="문의제목 작성해주세요.")
	private String title;
	@NotBlank(message ="문의글 작성해주세요.")
	private String content;

	public Question toQna() {
		return Question.builder()
				.id(id)
				.title(title)
				.content(content)
				.build();
	}	
	
	
}
