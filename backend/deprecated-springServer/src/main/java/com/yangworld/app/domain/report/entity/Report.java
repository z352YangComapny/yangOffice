package com.yangworld.app.domain.report.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    private int id;
    private int reporterId;
    private int reportedId;
    private String content;
    private LocalDateTime regDate;
}
