package com.yangworld.app.domain.member.dto;

import lombok.Data;

@Data
public class SignUpDto {
    private String username;
    private String password;
    private String name;
    private String gender;
    private String phone;
    private String email;
}
