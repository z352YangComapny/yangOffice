package com.ssoystory.feedservice.domain.feed.service;

import com.ssoystory.feedservice.common.s3.S3Service;
import com.ssoystory.feedservice.domain.feed.entity.Photo;
import com.ssoystory.feedservice.domain.feed.entity.PhotoFeed;
import com.ssoystory.feedservice.domain.feed.repository.FeedRepository;
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

    @Override
    public List<PhotoFeed> findPhotoFeedsByAuthorAndPageNO(Long AuthorId) {
        feedRepository.findPhotoFeedByAuthorIdOrderByRegDateDesc(AuthorId);
        return null;
    }

    @Override
    public void save(String content, List<MultipartFile> upFiles, String authName, Long AuthorId) {
        List<Photo> photos = s3Service.uploadFile(upFiles, authName);
        PhotoFeed photoFeed = PhotoFeed.builder()
                .Contents(content)
                .authorId(AuthorId)
                .Photos(new ArrayList<Photo>())
                .build();
        photos.forEach((photo) ->{
            Photo _photo = Photo.builder()
                    .URL(photo.getURL())
                    .photoFeed(photoFeed)
                    .build();
            photoFeed.getPhotos().add(_photo);
        });
        feedRepository.save(photoFeed);
    }


}
