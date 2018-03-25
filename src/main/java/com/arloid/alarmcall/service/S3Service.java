package com.arloid.alarmcall.service;

import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;

import java.io.File;
import java.util.List;

public interface S3Service {
    List<String> findAllBuckets();

    ListObjectsV2Result findAllAlarms();

    ListObjectsV2Result findAllObjects(String bucket);

    String upload(File file);

    S3Object findObjectByKey(String key);
}
