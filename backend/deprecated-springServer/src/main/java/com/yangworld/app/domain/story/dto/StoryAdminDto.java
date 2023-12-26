package com.yangworld.app.domain.story.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoryAdminDto {
    private int id;
    private String username;
    private String content;
    private LocalDateTime regDate;
}
