package com.ssoystory.memberservice.domain.profile.entity;

import com.ssoystory.memberservice.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @Embedded
    private ProfileId embeddedProfileId;
    private String profilePhotoURL;
    private String content;
    private int state;
}
