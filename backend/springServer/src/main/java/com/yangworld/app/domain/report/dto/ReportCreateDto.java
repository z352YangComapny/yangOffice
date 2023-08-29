package com.yangworld.app.domain.report.dto;

import javax.validation.constraints.NotBlank;

import com.yangworld.app.domain.report.entity.Report;

import lombok.Data;

@Data
public class ReportCreateDto {
    private int id;

    @NotBlank(message = "신고사유 입력")
    private String content;


    public Report toReport() {
        return Report.builder()
                .content(content)
                .build();
    }

}
