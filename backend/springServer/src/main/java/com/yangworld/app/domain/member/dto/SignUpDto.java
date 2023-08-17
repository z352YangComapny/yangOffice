package com.yangworld.app.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Date;

/**
 * 정규식으로 Valid 구체화예정
 * 개발환경에서는 not black 만 으로 설정.
 * */
@Data
public class SignUpDto {
    private int id;
    @NotBlank(message="아이디")
//    @Pattern(regexp = "^[a-zA-Z0-9]{8,}$", message="아이디는 영문자/숫자로만 8글자 이상이여야 합니다.")
    private String username;
    @NotBlank(message="비밀번호")
    private String password;
    @NotBlank(message="이름")
    private String name;
    @NotBlank(message="별명")
    private String nickname;
    @NotBlank(message="성별")
    private String gender;
    @NotBlank(message="연락처")
    private String phone;
    @NotBlank(message="Email")
    private String email;
    @NotBlank(message ="생일")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String birthday;
}