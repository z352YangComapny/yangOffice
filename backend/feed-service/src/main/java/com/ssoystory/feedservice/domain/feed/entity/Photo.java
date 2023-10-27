package com.ssoystory.feedservice.domain.feed.entity;

import jakarta.persistence.*;

@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "PhotoFeed_id")
    private PhotoFeed photoFeed;
    @Column
    private String URL;
}
