package com.ssoystory.feedservice.domain.comments.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssoystory.feedservice.domain.feed.entity.PhotoFeed;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude = "photoFeed")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long AuthorId;
    private String Content;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "photoFeed_id", insertable = false, updatable = false)
    @JsonIgnore
    @BatchSize(size = 2)
    private PhotoFeed photoFeed;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDate;

    @Column(name = "photofeed_id")
    private Long photoFeedId;

    public Comments(Long photoFeedId) {
        this.photoFeedId = photoFeedId;
    }
}