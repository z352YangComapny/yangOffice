package com.ssoystory.s3service.domain.service;

import com.ssoystory.s3service.common.NameBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3ServiceImpl implements S3Service{
    @Autowired
    NameBuilder nameBuilder;
    @Override
    public void save(MultipartFile file, String author) {
        String fileName = nameBuilder.build(file.getOriginalFilename(), author);
    }
}