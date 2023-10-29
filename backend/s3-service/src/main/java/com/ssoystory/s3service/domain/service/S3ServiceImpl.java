package com.ssoystory.s3service.domain.service;

import com.ssoystory.s3service.common.NameBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class S3ServiceImpl implements S3Service{
    @Autowired
    NameBuilder nameBuilder;
    @Override
    public void save(MultipartFile file, String author) {
        String fileName = nameBuilder.build(file.getOriginalFilename(), author);
    }

    @KafkaListener(topics = "s3", groupId="ssoystory")
    public void saveKafka(String message) throws IOException {
        log.info(message);
    }
}