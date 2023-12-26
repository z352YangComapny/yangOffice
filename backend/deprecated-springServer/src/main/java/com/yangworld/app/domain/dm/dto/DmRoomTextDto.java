package com.yangworld.app.domain.dm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DmRoomTextDto {
    private int id;
    private String participantNickname1;
    private String participantNickname2;
    private LocalDateTime LastTime;
    private String LastMessage;
}
