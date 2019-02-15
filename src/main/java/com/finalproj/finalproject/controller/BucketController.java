package com.finalproj.finalproject.controller;

import com.finalproj.finalproject.service.impl.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/storage")
public class BucketController {

    @Autowired
    AmazonClient awsS3Service;

    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam(value = "file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {

            String name=awsS3Service.uploadFile(file,true);
            return new ResponseEntity<>(name, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("geturl/{name}")
    public String getUrlFromName(@PathVariable("name") String name){
        return awsS3Service.getUrlFromFileName(name);
    }

}
