package com.yangworld.app.domain.profile.entity;

<<<<<<< HEAD:backend/springServer/src/main/java/com/yangworld/app/domain/profile/entity/State.java
public enum State {

	A, B, C, D, E;
}
=======
<<<<<<<< HEAD:backend/springServer/src/main/java/com/yangworld/app/domain/profile/entity/Profile.java
import java.util.List;

import com.yangworld.app.domain.attachment.entity.Attachment;
import com.yangworld.app.domain.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Profile {

	private int id;
	private int memberId;
	private State state;
	private String introduction;
}
========
public enum State {

    A, B, C, D, E;
}
>>>>>>>> 1.1.1:backend/deprecated-springServer/src/main/java/com/yangworld/app/domain/profile/entity/State.java
>>>>>>> 1.1.1:backend/deprecated-springServer/src/main/java/com/yangworld/app/domain/profile/entity/State.java
