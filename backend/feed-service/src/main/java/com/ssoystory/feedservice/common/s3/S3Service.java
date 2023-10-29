package com.ssoystory.feedservice.common.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ssoystory.feedservice.common.NameBuilder;
import com.ssoystory.feedservice.domain.feed.entity.Photo;
import com.ssoystory.feedservice.exception.s3.S3UploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final String path = "/ssoystory";

    @Transactional
    public List<Photo> uploadFile(List<MultipartFile> files, String authName) {
        List<Photo> photos = new ArrayList<>();
        String folderPath = path + "/" + authName;

        files.forEach(file->{
            String originalFileName = file.getOriginalFilename();
            String newFileName = NameBuilder.build(originalFileName);
            String fileUrl;
            ObjectMetadata metadata= new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            try {
                amazonS3Client.putObject(bucket,folderPath+"/"+newFileName,file.getInputStream(),metadata);
                fileUrl = "https://" + bucket + "/" + folderPath + "/" + newFileName;
            } catch (IOException e) {
                throw new S3UploadException("업로드 실패");
            }

            Photo photo = Photo.builder()
                    .URL(fileUrl)
                    .build();

            photos.add(photo);
        });
        return photos;
    }
}
