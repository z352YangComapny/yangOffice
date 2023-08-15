package com.yangworld.app.domain.dm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DmRoom {
    private int id;
    private int participant1;
    private int participant2;
    private LocalDateTime regDate;
}
