package com.ssoystory.storyservice.domain.story.repository;

import com.ssoystory.storyservice.domain.story.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {

    List<Story> findByAuthorIdInAndRegDateAfter(List<Long> authorIds, Timestamp twentyFourHoursAgo);

    void deleteStoryByAuthorIdAndId(Long id, long storyId);
}
