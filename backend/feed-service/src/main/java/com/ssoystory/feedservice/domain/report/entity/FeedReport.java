package com.ssoystory.feedservice.domain.report.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Id
    @Column(nullable = false)
    private Long reporterId;

    @Id
    @Column(nullable = false)
    private Long reportedFeedId;
}
