package com.yangworld.app.domain.OAuth.provider.naver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NaverResponse {
    private String resultcode;
    private String message;
    private NaverMemberInfo response;

}
