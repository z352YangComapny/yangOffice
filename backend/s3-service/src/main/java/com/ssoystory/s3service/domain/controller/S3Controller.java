package com.ssoystory.s3service.domain.controller;

import com.ssoystory.s3service.common.NameBuilder;
import com.ssoystory.s3service.domain.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/s3")
public class S3Controller {

    @Autowired
    S3Service s3Service;


    @PostMapping
    public void save(@RequestParam("image") MultipartFile file, @RequestParam("Author") String author){

        s3Service.save(file,author);
    }

    @DeleteMapping
    public void delete(){

    }

}
