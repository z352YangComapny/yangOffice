package com.yangworld.app.domain.comments.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.profile.entity.Profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QnaCommentCreateDto {

	private int id;
	private int writerId;
	private String content;
	private QnaCommentDto commentQna;
	private LocalDateTime regDate;
//	
//	public Comments toComments(){
//		return Comments.builder()
//				.id(id)
//				.writerId(writerId)
//				.content(content)
//				.regDate(regDate)
//				.build();
//				
//	}
}

