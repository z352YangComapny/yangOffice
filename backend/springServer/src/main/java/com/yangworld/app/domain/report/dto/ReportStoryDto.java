package com.yangworld.app.domain.report.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportStoryDto {
    private int id;
    private int reporterId;
    private int reportedId;
    private String reportedName;
    private int storyId;
    private String content;
    private LocalDateTime regDate;
}

