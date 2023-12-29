package com.yangworld.app.domain.OAuth.provider.kakao;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KakaoProperties {
    private String nickname;
    private String profile_image;
    private String thumbnail_image;
}
