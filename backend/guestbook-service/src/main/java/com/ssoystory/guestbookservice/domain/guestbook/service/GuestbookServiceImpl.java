package com.ssoystory.guestbookservice.domain.guestbook.service;

import com.ssoystory.guestbookservice.commons.kafka.service.KafkaConsumerService;
import com.ssoystory.guestbookservice.commons.kafka.service.KafkaProducerService;
import com.ssoystory.guestbookservice.domain.guestbook.dto.DeleteGuestbookDto;
import com.ssoystory.guestbookservice.domain.guestbook.dto.GuestbookDto;
import com.ssoystory.guestbookservice.domain.guestbook.entity.Guestbook;
import com.ssoystory.guestbookservice.domain.guestbook.repository.GuestbookRepository;
import com.ssoystory.guestbookservice.exception.AuthorizationException;
import com.ssoystory.guestbookservice.exception.CannotFindGuestbookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuestbookServiceImpl implements GuestbookService{
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private KafkaConsumerService kafkaConsumerService;
    @Autowired
    private GuestbookRepository guestbookRepository;

    @Override
    public void save(Long authId, GuestbookDto guestbookDto) throws InterruptedException {
        kafkaProducerService.sendUsername(guestbookDto.getOwnerName());
        Long ownerId = Long.parseLong(kafkaConsumerService.receiveUserId());
        Guestbook guestbook = Guestbook.builder()
                .authId(authId)
                .ownerId(ownerId)
                .content(guestbookDto.getContent())
                .build();
        guestbookRepository.save(guestbook);
    }

    @Override
    public Page<Guestbook> findById(String username, Pageable pageable) throws InterruptedException {
        kafkaProducerService.sendUsername(username);
        Long ownerId = Long.parseLong(kafkaConsumerService.receiveUserId());
        Page<Guestbook> guestbooks = guestbookRepository.findByOwnerId(ownerId, pageable);
        return guestbooks;
    }

    @Override
    public void delete(Long authId, DeleteGuestbookDto guestbookDto) throws CannotFindGuestbookException, AuthorizationException{
         Optional<Guestbook> guestbook = guestbookRepository.findById(guestbookDto.getId());
         if(guestbook.isEmpty()) {
             throw new CannotFindGuestbookException("없는 방명록");
         }

         Guestbook guestbook1 = guestbook.get();
         if(guestbook1.getId().equals(authId)  || guestbook1.getOwnerId().equals(authId)){
             guestbookRepository.deleteById(guestbookDto.getId());
         }else {
             throw new AuthorizationException("403");
         }
    }
}
