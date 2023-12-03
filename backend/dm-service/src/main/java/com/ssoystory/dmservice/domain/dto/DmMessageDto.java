package com.ssoystory.dmservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DmMessageDto extends MessageDto{
    private Long receiverId;
    private Long senderId;
    private String content;
}
