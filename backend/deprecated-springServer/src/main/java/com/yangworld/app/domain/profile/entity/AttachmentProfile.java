package com.yangworld.app.domain.profile.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentProfile {

	private int attachmentId;
    private int profileId;

}