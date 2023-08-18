package com.yangworld.app.domain.comments.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.photoFeed.entity.PhotoFeed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateDto {

	
	private int id;
	private int writerId;
	private String content;
	private PhotoFeed photoFeed;
	private CommentFeedDto commentFeed;
	private List<Comments> comments;
	private LocalDateTime regDate;
	
}
