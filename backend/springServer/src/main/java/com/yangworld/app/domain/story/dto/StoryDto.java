package com.yangworld.app.domain.story.dto;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryDto {
	private int writerId;
	@Pattern(regexp = "^.{1,100}$", message = "1글자 이상 100글자 이하로 작성해주세요")
	private String content;
}
