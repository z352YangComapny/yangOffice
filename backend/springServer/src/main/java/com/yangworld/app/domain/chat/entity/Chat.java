package com.yangworld.app.domain.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    private int id;
    private int memberId;
    private String chatContent;
    private LocalDateTime sendDate;

    private String validYN;

}
