package com.arloid.alarmcall.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "alarm.internal", ignoreUnknownFields = false)
public class AlarmCallProperties {
  private int maxCallAttempts;
}
