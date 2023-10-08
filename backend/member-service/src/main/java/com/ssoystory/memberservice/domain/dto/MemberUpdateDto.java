package com.ssoystory.memberservice.domain.dto;

import com.ssoystory.memberservice.domain.entity.Authority;
import com.ssoystory.memberservice.domain.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberUpdateDto {
    private Long Id;
    private String username;
    private String name;
    private String password;
    private String nickname;
    private Date birthday;
    private Gender gender;
    private String phone;
    private String email;
}
