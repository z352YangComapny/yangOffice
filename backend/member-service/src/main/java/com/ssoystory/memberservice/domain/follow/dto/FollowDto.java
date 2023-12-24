package com.ssoystory.memberservice.domain.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowDto {
    private Long userId;
    private Long FolloweeId;
}
