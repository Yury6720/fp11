package com.kuzminski.configuration.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties
@ConfigurationProperties("token")
@Configuration
@Getter
@Setter
public class TokenConfig {

  private String jwtSecret;

  private int jwtExpirationInMs;
}
