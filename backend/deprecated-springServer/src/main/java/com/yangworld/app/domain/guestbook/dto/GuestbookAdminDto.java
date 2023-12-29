package com.yangworld.app.domain.guestbook.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestbookAdminDto {

    private int id;
    private String writer;
    private String toMember;
    private String content;
    private LocalDateTime regDate;


}
