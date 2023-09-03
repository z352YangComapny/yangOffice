package com.yangworld.app.domain.OAuth.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OAuthDto {
    private String provider;
    private String kakaoCode;
    private String naverCode;
    private String naverState;
}
