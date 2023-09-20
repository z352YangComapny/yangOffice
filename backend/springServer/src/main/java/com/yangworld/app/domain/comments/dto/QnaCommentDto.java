package com.yangworld.app.domain.comments.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QnaCommentDto {

	private int commentId;
	private int questionId;

}
