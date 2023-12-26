package com.yangworld.app.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yangworld.app.domain.member.entity.Member;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Data
@Builder
public class UpdateDto {


    @NotBlank(message="비밀번호")
    private String password;
    @NotBlank(message="별명")
    private String nickname;
    @NotBlank(message="연락처")
    private String phone;
    @NotBlank(message="Email")
    private String email;
    @NotBlank(message ="생일")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String birthday;

}
