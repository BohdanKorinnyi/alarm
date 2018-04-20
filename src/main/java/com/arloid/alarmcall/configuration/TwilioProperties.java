package com.arloid.alarmcall.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "twilio", ignoreUnknownFields = false)
public class TwilioProperties {
  private String sid;
  private String token;
  private String number;
  private String alarmEndpoint;
}
