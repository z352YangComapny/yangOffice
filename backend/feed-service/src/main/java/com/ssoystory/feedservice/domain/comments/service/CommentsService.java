package com.ssoystory.feedservice.domain.comments.service;

import com.ssoystory.feedservice.domain.comments.dto.CommentsDto;

public interface CommentsService {
    void save(CommentsDto content, long id);
}
