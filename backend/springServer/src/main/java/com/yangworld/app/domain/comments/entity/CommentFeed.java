package com.yangworld.app.domain.comments.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentFeed {
	
	private int commentsId;
	private int photoFeedId;

}
