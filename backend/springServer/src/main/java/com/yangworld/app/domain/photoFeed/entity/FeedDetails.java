package com.yangworld.app.domain.photoFeed.entity;

import java.util.List;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.comments.entity.Comments;
import com.yangworld.app.domain.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class FeedDetails extends PhotoFeed{

	private Member member;
	private String nickName;
	private int attachCount;
	private int likeCount;
	private List<Comments> comments;
	private List<Attachment> attachments;
	
}