package com.ssoystory.feedservice.domain.feed.service;

import com.google.gson.Gson;
import com.ssoystory.feedservice.common.kafka.KafkaConsumerService;
import com.ssoystory.feedservice.common.kafka.KafkaProducerService;
import com.ssoystory.feedservice.common.s3.S3Service;
import com.ssoystory.feedservice.domain.feed.dto.IdPageDto;
import com.ssoystory.feedservice.domain.feed.entity.Photo;
import com.ssoystory.feedservice.domain.feed.entity.PhotoFeed;
import com.ssoystory.feedservice.domain.feed.repository.FeedRepository;
import com.ssoystory.feedservice.exception.feed.CannotFindException;
import com.ssoystory.feedservice.exception.feed.ConvertUsernameToIDException;
import com.ssoystory.feedservice.exception.feed.ForbiddenException;
import com.ssoystory.feedservice.exception.s3.S3UploadException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class FeedServiceImpl implements FeedService{
    @Autowired
    private S3Service s3Service;
    @Autowired
    private FeedRepository feedRepository;
    @Autowired
    private KafkaProducerService kafkaProducerService;
    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    Gson gson = new Gson();

    @Override
    public List<PhotoFeed> findPhotoFeedsByAuthorAndPageNO(String username, Pageable pageable) throws ConvertUsernameToIDException {

        kafkaProducerService.sendUsernameAndPageNo(username,1);
        IdPageDto idPageDto;
        try {
            String _idPageDto=kafkaConsumerService.receiveUserId();
            idPageDto = gson.fromJson(_idPageDto, IdPageDto.class);
            log.info("Kafka receive Message = {}",idPageDto);
        } catch (InterruptedException e){
            throw new ConvertUsernameToIDException(e.getMessage());
        }
        Page<PhotoFeed> photoFeeds = feedRepository.findPhotoFeedByAuthorIdOrderByRegDateDesc(idPageDto.getUserId(), pageable);
        return photoFeeds.getContent();
    }

    @Override
    public void save(String content, List<MultipartFile> upFiles, String authName, Long AuthorId) throws S3UploadException {
        try {
            List<Photo> photos = s3Service.uploadFile(upFiles, authName);
            PhotoFeed photoFeed = PhotoFeed.builder()
                    .Contents(content)
                    .authorId(AuthorId)
                    .Photos(new HashSet<Photo>())
                    .build();
            photos.forEach((photo) -> {
                Photo _photo = Photo.builder()
                        .URL(photo.getURL())
                        .photoFeed(photoFeed)
                        .build();
                photoFeed.getPhotos().add(_photo);
            });
            feedRepository.save(photoFeed);
        } catch (S3UploadException s3UploadException){
            throw s3UploadException;
        }
    }

    @Override
    public void update(long id, String content, Long authId) {
        Optional<PhotoFeed> photoFeed = feedRepository.findById(id);
        PhotoFeed feed = photoFeed.get();
        feed.setContents(content);
        feedRepository.save(feed);
    }

    @Override
    public void delete(long id, Long authId) throws ForbiddenException {
        Optional<PhotoFeed> photoFeed = feedRepository.findById(id);
        if (photoFeed.get().getId()==authId){
            feedRepository.deleteById(id);
        } else if(photoFeed.isEmpty()) {
            throw new CannotFindException("해당 피드 조회 불가");
        } else {
            throw new ForbiddenException("권한 없음");
        }
    }

}
