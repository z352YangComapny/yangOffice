package com.yangworld.app.domain.OAuth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NaverDto {
    private String provider;
    private String state;
    private String naverCode;
}
