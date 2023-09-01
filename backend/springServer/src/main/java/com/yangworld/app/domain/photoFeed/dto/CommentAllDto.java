package com.yangworld.app.domain.photoFeed.dto;

import com.yangworld.app.domain.comments.entity.Comments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentAllDto {

    private int id;
    private int writerId;
    private String nickName;
    private String content;
    private LocalDateTime regDate;

}
