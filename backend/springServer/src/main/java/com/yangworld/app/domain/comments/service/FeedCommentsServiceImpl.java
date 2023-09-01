package com.yangworld.app.domain.comments.service;

import com.yangworld.app.config.auth.PrincipalDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("FeedCommentsServiceImpl")
@Slf4j
public class FeedCommentsServiceImpl implements CommentsService{


    @Override
    public int commentsUpdate(PrincipalDetails principalDetails, int id) {
        int writerId = principalDetails.getId();



        return 0;
    }
}
