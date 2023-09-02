package com.yangworld.app.domain.OAuth.provider.kakao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KaKaoMemberInfo {
    private long id;
    private String connected_at;
    private KakaoProperties properties;
    private KakaoAccount kakao_account;
}
