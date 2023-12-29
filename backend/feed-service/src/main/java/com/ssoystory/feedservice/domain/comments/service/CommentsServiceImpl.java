package com.ssoystory.feedservice.domain.comments.service;

import com.ssoystory.feedservice.domain.comments.dto.CommentsDto;
import com.ssoystory.feedservice.domain.comments.entity.Comments;
import com.ssoystory.feedservice.domain.comments.repository.CommentsRepository;
import com.ssoystory.feedservice.domain.feed.entity.PhotoFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImpl implements CommentsService{
    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public void save(CommentsDto content, long id) {
        Comments comments = Comments.builder()
                .AuthorId(id)
                .photoFeedId(content.getFeedId())
                .Content(content.getContent())
                .build();
        commentsRepository.save(comments);
    }
}
