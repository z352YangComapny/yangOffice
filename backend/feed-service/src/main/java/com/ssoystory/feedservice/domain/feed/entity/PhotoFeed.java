package com.ssoystory.feedservice.domain.feed.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssoystory.feedservice.domain.comments.entity.Comments;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class PhotoFeed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    @OneToMany(mappedBy = "photoFeed", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @BatchSize( size = 5)
    private Set<Photo> Photos;

    private String Contents;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul" )
    private Timestamp regDate;

    private int Likes;

    @Column(nullable = false)
    private Long authorId;

    @OneToMany(mappedBy = "photoFeed", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @BatchSize(size = 50)
    private Set<Comments> comments;
}
