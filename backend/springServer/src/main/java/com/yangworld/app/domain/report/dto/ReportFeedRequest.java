package com.yangworld.app.domain.report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportFeedRequest {
    private int feedId;
    private int commentsId;
    private String content;
}
