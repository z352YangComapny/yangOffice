package com.yangworld.app.domain.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDetailDto {
    private int id;
    private String type;
    private String writer;
    private String content;
    private LocalDate regDate;

}
