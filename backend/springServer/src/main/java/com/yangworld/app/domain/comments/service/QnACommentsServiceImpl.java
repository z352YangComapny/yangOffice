package com.yangworld.app.domain.comments.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Service("QnACommentsServiceImpl")
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class QnACommentsServiceImpl {
}
