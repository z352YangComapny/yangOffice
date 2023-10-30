package com.ssoystory.feedservice.domain.comments.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssoystory.feedservice.domain.feed.entity.PhotoFeed;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long AuthorId;
    private String Content;

    @ManyToOne
    @JoinColumn(name = "photofeed_id")
    private PhotoFeed photoFeed;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDate;
}