package com.yangworld.app.domain.photoFeed.dto;

import com.yangworld.app.domain.attachment.entity.Attachment;
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
public class PhotoFeedAll {

    private int id;
    private String content;
    private int commentCount;
    private int likeCount;
    private List<CommenttDto> comments;
    private List<AttachmentDto> attachments;
    private LocalDateTime regDate;

}
