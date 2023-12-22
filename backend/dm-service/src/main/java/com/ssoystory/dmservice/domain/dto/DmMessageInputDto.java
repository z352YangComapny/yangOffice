package com.ssoystory.dmservice.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DmMessageInputDto extends MessageDto{
    private String nickname;
    private Long senderId;
    private String content;
    private String senderNickname;

}