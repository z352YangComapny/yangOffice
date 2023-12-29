package com.ssoystory.storyservice.domain.story.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStoryDto {
    private String content;
    private Timestamp regDate;
}
