package com.ssoystory.memberservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConvertUsernameToIdDto {
    private String username;
    private int pageNo;
}
