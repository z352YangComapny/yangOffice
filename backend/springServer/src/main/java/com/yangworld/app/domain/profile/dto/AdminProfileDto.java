package com.yangworld.app.domain.profile.dto;

import com.yangworld.app.domain.profile.entity.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminProfileDto {
    private int id;
    private String username;
    private State state;
    private String introduction;
    private String renamedFilename;
    private int follower;
    private int followee;
}
