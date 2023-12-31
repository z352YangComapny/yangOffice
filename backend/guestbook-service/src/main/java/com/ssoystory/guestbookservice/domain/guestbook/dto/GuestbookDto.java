package com.ssoystory.guestbookservice.domain.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuestbookDto {
    private String content;
    private String ownerName;
}
