package com.arloid.alarmcall.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "amazon.s3", ignoreUnknownFields = false)
public class AmazonS3Configuration {
    private String accessKey;
    private String secretKey;
    private String bucket;
}
