package com.ssoystory.gateway.jwt;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshAccessTokenDTO {
    private int status;
    private String newAccessTokenOrElseErrorMessage;
}
