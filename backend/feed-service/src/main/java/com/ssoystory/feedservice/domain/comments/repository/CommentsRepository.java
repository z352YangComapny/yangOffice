package com.ssoystory.feedservice.domain.comments.repository;

import com.ssoystory.feedservice.domain.comments.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
