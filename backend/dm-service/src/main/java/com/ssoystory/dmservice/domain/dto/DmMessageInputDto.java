package com.ssoystory.dmservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DmMessageInputDto extends MessageDto{
    private String nickname;
    private Long senderId;
    private String content;
    private Long senderNickname;
}