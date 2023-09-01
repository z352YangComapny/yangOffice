package com.yangworld.app.domain.profile.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.yangworld.app.domain.profile.entity.State;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileAll {

	private int id;
	private State state;
    private String introduction;
	private List<AttachmentDto> attachments;
    private LocalDateTime regDate;
}
