package com.ssoystory.memberservice.domain.profile.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Profile {

    @EmbeddedId
    private ProfileId id;

    private String profilePhotoURL;
    private String content;
    private int state;
}
