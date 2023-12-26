package com.yangworld.app.domain.OAuth.provider.kakao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoProfile {
    private String nickname;
    private String thumbnail_image_url;
    private String profile_image_url;
    private boolean is_default_image;
}
