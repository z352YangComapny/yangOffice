package com.ssoystory.feedservice.domain.report.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedReportId {
    private Long reporterId;
    private Long reportedFeedId;
}
