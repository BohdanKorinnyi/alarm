package com.arloid.alarmcall.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.arloid.alarmcall.configuration.AmazonS3Configuration;
import com.arloid.alarmcall.service.S3Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class S3ServiceImpl implements S3Service {
    private final AmazonS3 amazonS3;
    private final AmazonS3Configuration amazonS3Configuration;

    @Override
    public List<String> findAllBuckets() {
        return amazonS3.listBuckets()
                .stream()
                .map(Bucket::getName)
                .collect(toList());
    }

    @Override
    public ListObjectsV2Result findAllAlarms() {
        return amazonS3.listObjectsV2(amazonS3Configuration.getBucket());
    }

    @Override
    public ListObjectsV2Result findAllObjects(String bucket) {
        return amazonS3.listObjectsV2(bucket);
    }

    @Override
    public String upload(File file) {
        try {
            PutObjectResult result = amazonS3.putObject(new PutObjectRequest(amazonS3Configuration.getBucket(), file.getPath(), file));
            return amazonS3.getUrl(amazonS3Configuration.getBucket(), file.getPath()).toString();
        } catch (Exception e) {
            log.error("error occurred during uploading", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public S3Object findObjectByKey(String key) {
        return amazonS3.getObject(amazonS3Configuration.getBucket(), key);
    }
}
