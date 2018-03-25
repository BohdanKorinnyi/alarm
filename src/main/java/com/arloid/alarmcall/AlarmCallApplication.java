package com.arloid.alarmcall;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.arloid.alarmcall.configuration.TwilioProperties;
import com.google.common.base.Predicates;
import com.twilio.Twilio;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

@EnableSwagger2
@AllArgsConstructor
@SpringBootApplication
public class AlarmCallApplication {

    private final TwilioProperties twilioProperties;

    public static void main(String[] args) {
        SpringApplication.run(AlarmCallApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Alarm Call")
                        .description("Microservice's endpoints")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    @PostConstruct
    public void initTwilio() {
        Twilio.init(twilioProperties.getSid(), twilioProperties.getToken());
    }

    @Bean
    public AWSCredentialsProvider credentialsProvider() {
        return new EnvironmentVariableCredentialsProvider();
    }

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(credentialsProvider())
                .withRegion(Regions.DEFAULT_REGION)
                .build();
    }
}
