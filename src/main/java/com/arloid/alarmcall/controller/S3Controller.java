package com.arloid.alarmcall.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("s3")
public class S3Controller {
    private static final String BUCKET = "alarm-twiml";
    private final AmazonS3 amazonS3;

    @PostMapping("buckets")
    public ResponseEntity getFirstObject() {
        ListObjectsV2Result list = amazonS3.listObjectsV2(BUCKET);
        S3Object object = amazonS3.getObject(list.getBucketName(),
                list.getObjectSummaries().get(0).getKey());
        return ResponseEntity.ok()
                .contentLength(object.getObjectMetadata().getContentLength())
                .contentType(MediaType.TEXT_XML)
                .body(new InputStreamResource(object.getObjectContent()));
    }

    @GetMapping("objects")
    private ResponseEntity objects() {
        return ResponseEntity.ok(amazonS3.listObjectsV2(BUCKET));
    }
}
