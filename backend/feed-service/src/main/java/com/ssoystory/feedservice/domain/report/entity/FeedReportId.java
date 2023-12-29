package com.ssoystory.feedservice.domain.report.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedReportId implements Serializable {
    private Long reporterId;
    private Long reportedFeedId;
}