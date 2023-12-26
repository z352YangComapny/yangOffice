package com.ssoystory.memberservice.domain.follow.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowKey implements Serializable {
    private Long followerId;
    private Long followeeId;
}
