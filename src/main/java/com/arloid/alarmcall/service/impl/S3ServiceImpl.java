package com.arloid.alarmcall.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.arloid.alarmcall.configuration.AmazonS3Configuration;
import com.arloid.alarmcall.service.S3Service;
import com.google.common.io.Files;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

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
    public String upload(MultipartFile file) {
        try {
            String fileKey = UUID.randomUUID().toString() + "." + Files.getFileExtension(file.getOriginalFilename());
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());
            amazonS3.putObject(new PutObjectRequest(amazonS3Configuration.getBucket(), fileKey, file.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            return amazonS3.getUrl(amazonS3Configuration.getBucket(), fileKey).toString();
        } catch (Exception e) {
            log.error("error occurred during uploading", e);
            throw new RuntimeException("Error occurred during uploading alarm");
        }
    }

    @Override
    public S3Object findObjectByKey(String key) {
        return amazonS3.getObject(amazonS3Configuration.getBucket(), key);
    }
}
