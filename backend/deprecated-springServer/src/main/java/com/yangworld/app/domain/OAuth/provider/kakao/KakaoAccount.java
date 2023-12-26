package com.yangworld.app.domain.OAuth.provider.kakao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KakaoAccount {
    private boolean profile_nickname_needs_agreement;
    private boolean profile_image_needs_agreement;
    private KakaoProfile profile;
    private boolean has_email;
    private boolean email_needs_agreement;
    private boolean is_email_valid;
    private boolean is_email_verified;
    private String email;
}
