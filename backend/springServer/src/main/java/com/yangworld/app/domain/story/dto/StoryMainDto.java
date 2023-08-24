package com.yangworld.app.domain.story.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryMainDto {
	private int id;
	private int writerId;
	@NotBlank(message = "내용을 입력해주세요")
	private String content;
	private LocalDateTime regDate;
}
