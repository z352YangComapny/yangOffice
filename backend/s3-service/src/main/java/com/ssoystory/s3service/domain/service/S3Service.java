package com.ssoystory.s3service.domain.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
    void save(MultipartFile file, String author);
}
