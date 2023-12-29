package com.yangworld.app.domain.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestbookDailyDto {

    private LocalDate guestbookDate;
    private int guestbookCount;
}
