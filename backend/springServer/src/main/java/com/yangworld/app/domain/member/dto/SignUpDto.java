package com.yangworld.app.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 정규식으로 Valid 구체화예정
 * 개발환경에서는 not black 만 으로 설정.
 * */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    private int id;
    @NotBlank(message="아이디")
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
}