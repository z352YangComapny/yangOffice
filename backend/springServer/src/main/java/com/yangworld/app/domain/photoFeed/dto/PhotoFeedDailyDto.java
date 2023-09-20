package com.yangworld.app.domain.photoFeed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoFeedDailyDto {

    private LocalDate photofeedDate;
    private int photofeedCount;
}
