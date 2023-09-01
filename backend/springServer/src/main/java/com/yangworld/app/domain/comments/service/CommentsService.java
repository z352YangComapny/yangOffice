package com.yangworld.app.domain.comments.service;

import com.yangworld.app.config.auth.PrincipalDetails;

public interface CommentsService {
    int commentsUpdate(PrincipalDetails principalDetails, int id);
}
