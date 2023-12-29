package com.ssoystory.dmservice.domain.repository;

import com.ssoystory.dmservice.domain.entity.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DmMessageRepository extends JpaRepository<Message , Long> {

    @Query("SELECT m FROM Message m " +
            "WHERE (m.senderId = :senderId AND m.receiverId = :receiverId) " +
            "   OR (m.senderId = :receiverId AND m.receiverId = :senderId) " +
            "ORDER BY m.regDate DESC")
    List<Message> findMessagesBySenderIdAndReceiverIdOrderByRegDate(@Param("senderId") Long senderId,
                                                      @Param("receiverId") Long receiverId,
                                                      Pageable pageable);
}