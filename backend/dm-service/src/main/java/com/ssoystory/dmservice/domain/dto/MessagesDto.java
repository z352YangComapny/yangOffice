package com.ssoystory.dmservice.domain.dto;

import com.ssoystory.dmservice.domain.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessagesDto {
    private List<Message> messageList;
    private Long count;
}
