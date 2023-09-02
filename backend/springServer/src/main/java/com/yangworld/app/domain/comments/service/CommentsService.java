package com.yangworld.app.domain.comments.service;

import com.yangworld.app.config.auth.PrincipalDetails;

public interface CommentsService {
    int commentsUpdate(PrincipalDetails principalDetails, int commentId, String content);

    int commentsDelete(PrincipalDetails principalDetails, int commentId);

    int commentCreate(PrincipalDetails principalDetails, String content, int feedId);
}
