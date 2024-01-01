package com.ssoystory.storyservice.domain.story.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ssoystory.storyservice.common.kafka.service.KafkaConsumerService;
import com.ssoystory.storyservice.common.kafka.service.KafkaProducerService;
import com.ssoystory.storyservice.domain.story.dto.CreateStoryDto;
import com.ssoystory.storyservice.domain.story.entity.Story;
import com.ssoystory.storyservice.domain.story.repository.StoryRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
public class StoryServiceImpl implements StoryService {
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private KafkaConsumerService kafkaConsumerService;
    @Autowired
    private KafkaProducerService kafkaProducerService;


    @Override
    @CircuitBreaker(name = "getStoryList", fallbackMethod = "fallbackGetStoryMethod")
    public List<Story> getStoryList(Long id) throws InterruptedException {
        kafkaProducerService.getFolloweeList(id);

        String _Dto = kafkaConsumerService.receiveFolloweeList();
        Gson gson = new Gson();
        List<Long> followees = gson.fromJson(_Dto,new TypeToken<List<Long>>() {}.getType());
        log.info("{}",followees);

        LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);
        Timestamp timestampTwentyFourHoursAgo = Timestamp.valueOf(twentyFourHoursAgo);

        List<Story> recentStories = storyRepository.findByAuthorIdInAndRegDateAfter(List.of(id), timestampTwentyFourHoursAgo);
        log.info("Stories for followees: {}", recentStories);
        return recentStories;
    }

    @Override
    public void save(long id, CreateStoryDto createStoryDto) {
        Story story = Story.builder()
                .authorId(id)
                .content(createStoryDto.getContent())
                .build();
        storyRepository.save(story);
    }

    @Override
    public void deleteById(Long id, long storyId) {
        storyRepository.deleteStoryByAuthorIdAndId(id, storyId);
    }

    public List<Story> fallbackGetStoryMethod(Long id, Throwable throwable) throws InterruptedException {
        log.error("Circuit Breaker is open. Fallback method called for getStoryList with id: {}", id);
        log.error("throwable:{}", throwable.getMessage());
        throw new InterruptedException("카프카 통신 오류");
    }
}
