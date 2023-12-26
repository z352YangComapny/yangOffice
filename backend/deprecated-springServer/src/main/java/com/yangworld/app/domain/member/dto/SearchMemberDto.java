package com.yangworld.app.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchMemberDto {
    private String username;
    private String nickname;
    private String renamedFilename;
}
