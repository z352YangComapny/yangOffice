package com.ssoystory.feedservice.domain.feed.service;

import com.google.gson.Gson;
import com.ssoystory.feedservice.common.kafka.KafkaConsumerService;
import com.ssoystory.feedservice.common.kafka.KafkaProducerService;
import com.ssoystory.feedservice.common.s3.S3Service;
import com.ssoystory.feedservice.domain.feed.dto.IdPageDto;
import com.ssoystory.feedservice.domain.feed.entity.Photo;
import com.ssoystory.feedservice.domain.feed.entity.PhotoFeed;
import com.ssoystory.feedservice.domain.feed.repository.FeedRepository;
import com.ssoystory.feedservice.exception.feed.ConvertUsernameToIDException;
import com.ssoystory.feedservice.exception.s3.S3UploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Service
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
    public List<PhotoFeed> findPhotoFeedsByAuthorAndPageNO(String username) throws ConvertUsernameToIDException {

        kafkaProducerService.sendUsernameAndPageNo(username,1);
        IdPageDto idPageDto;
        try {
            String _idPageDto=kafkaConsumerService.receiveUserId();
            idPageDto = gson.fromJson(_idPageDto, IdPageDto.class);
        } catch (InterruptedException e){
            throw new ConvertUsernameToIDException(e.getMessage());
        }
        List<PhotoFeed> photoFeeds = feedRepository.findPhotoFeedByAuthorIdOrderByRegDateDesc(idPageDto.getAuthorId());
        return photoFeeds;
    }

    @Override
    public void save(String content, List<MultipartFile> upFiles, String authName, Long AuthorId) throws S3UploadException {
        try {
            List<Photo> photos = s3Service.uploadFile(upFiles, authName);
            PhotoFeed photoFeed = PhotoFeed.builder()
                    .Contents(content)
                    .authorId(AuthorId)
                    .Photos(new ArrayList<Photo>())
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
    public void update(String content, List<MultipartFile> upFiles, String auth, Long authId) {

    }


}
