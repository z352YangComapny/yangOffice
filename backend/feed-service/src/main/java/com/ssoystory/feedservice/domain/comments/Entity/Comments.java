package com.ssoystory.feedservice.domain.comments.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssoystory.feedservice.domain.feed.entity.PhotoFeed;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long AuthorId;
    private String Content;

    @ManyToOne
    @JoinColumn(name = "PhotoFeed_id")
    private PhotoFeed photoFeed;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDate;
}
