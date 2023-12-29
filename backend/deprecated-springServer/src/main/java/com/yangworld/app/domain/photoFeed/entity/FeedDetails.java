package com.yangworld.app.domain.profile.entity;

import java.util.List;

import com.yangworld.app.domain.attachment.entity.Attachment;
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
<<<<<<< HEAD:backend/springServer/src/main/java/com/yangworld/app/domain/photoFeed/entity/FeedDetails.java
public class FeedDetails extends PhotoFeed{

	private Member member;
	private String nickName;
	private int attachCount;
	private int likeCount;
	private List<Comments> comments;
	private List<Attachment> attachments;
	
=======
<<<<<<<< HEAD:backend/deprecated-springServer/src/main/java/com/yangworld/app/domain/profile/entity/ProfileDetails.java
public class ProfileDetails extends Profile{
	
	private int id;
========
public class FeedDetails extends PhotoFeed {

	private Member member;
>>>>>>>> 1.1.1:backend/deprecated-springServer/src/main/java/com/yangworld/app/domain/photoFeed/entity/FeedDetails.java
	private int attachCount;
	private List<Attachment> attachments;
	
	
>>>>>>> 1.1.1:backend/deprecated-springServer/src/main/java/com/yangworld/app/domain/photoFeed/entity/FeedDetails.java
}