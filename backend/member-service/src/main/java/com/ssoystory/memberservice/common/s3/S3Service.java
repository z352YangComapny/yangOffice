package com.ssoystory.memberservice.common.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ssoystory.memberservice.common.NameBuilder;
import com.ssoystory.memberservice.exception.S3UploadException;
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
    private final String path = "ssoystory";

    @Transactional
    public String uploadFile(MultipartFile file, String authName) throws S3UploadException, IOException {
        String folderPath = path + "/" + authName;
        String originalFileName = file.getOriginalFilename();
        String newFileName = NameBuilder.build(originalFileName);
        ObjectMetadata metadata= new ObjectMetadata();
        metadata.setContentType("image/jpeg");
        metadata.setContentLength(file.getSize());
        amazonS3Client.putObject(bucket,folderPath+"/"+newFileName,file.getInputStream(),metadata);
        return "https://gryu-dev.s3.ap-northeast-2.amazonaws.com/ssoystory/"+authName+"/"+newFileName;
    }
}
