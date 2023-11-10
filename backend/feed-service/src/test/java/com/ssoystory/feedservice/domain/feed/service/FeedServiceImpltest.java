package com.ssoystory.feedservice.domain.feed.service;

import com.ssoystory.feedservice.common.kafka.KafkaConsumerService;
import com.ssoystory.feedservice.common.kafka.KafkaProducerService;
import com.ssoystory.feedservice.domain.feed.repository.FeedRepository;
import com.ssoystory.feedservice.domain.feed.service.FeedServiceImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

public class FeedServiceImpltest {
    private FeedServiceImpl feedService;
    @Mock
    private KafkaConsumerService kafkaConsumerService;
    @Mock
    private KafkaProducerService kafkaProducerService;
    @Mock
    private FeedRepository feedRepository;

    @ParameterizedTest
    public void findPhotoFeedsByAuthorAndPageNOTest(String username, int pageNo) {

    }

}
