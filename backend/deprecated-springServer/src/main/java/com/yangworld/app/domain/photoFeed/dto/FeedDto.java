package com.yangworld.app.domain.photoFeed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedDto {
    private int id;
    private String username;
    private String content;
    private LocalDateTime regDate;
    private List<String> attachmentNames;
}