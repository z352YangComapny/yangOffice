package com.yangworld.app.domain.report.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    private int id;
    private int reporterId;
    private int reportedId;
    private String content;
    private LocalDateTime regDate;
}
