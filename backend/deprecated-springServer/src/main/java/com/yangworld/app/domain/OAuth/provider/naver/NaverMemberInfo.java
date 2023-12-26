package com.yangworld.app.domain.OAuth.provider.naver;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NaverMemberInfo {

    private String id;
    private String nickname;
    private String age;
    private String gender;
    private String email;
    private String mobile;

    @SerializedName("mobile_e164")
    private String mobileE164;

    private String name;
    private String birthday;
    private String birthyear;



}
