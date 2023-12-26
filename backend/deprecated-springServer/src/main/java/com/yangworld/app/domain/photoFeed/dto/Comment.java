package com.yangworld.app.domain.photoFeed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int id;
    private int writerId;
    private String content;
    private LocalDateTime regDate;
}
