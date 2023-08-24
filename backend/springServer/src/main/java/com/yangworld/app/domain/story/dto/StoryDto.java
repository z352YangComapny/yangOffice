package com.yangworld.app.domain.story.dto;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

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
	private String content;
}
