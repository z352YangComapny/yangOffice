package com.ssoystory.feedservice.domain.feed.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssoystory.feedservice.domain.comments.Entity.Comments;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;


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
    @OneToMany(mappedBy = "PhotoFeed", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("PhotoFeed")
    private List<Photo> Photos;

    private String Contents;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDate;

    private int Likes;

    @Column(nullable = false)
    private Long AuthorId;

    @OneToMany(mappedBy = "PhotoFeed", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("PhotoFeed")
    private List<Comments> comments;
}
