package com.ssoystory.memberservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConvertedUsernameToIdDto {
    private Long userId;
    private int pageNo;
}
