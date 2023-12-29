package com.ssoystory.dmservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DmMessageOutputDto extends MessageDto{
    private Long receiverId;
    private Long senderId;
    private String content;
}
