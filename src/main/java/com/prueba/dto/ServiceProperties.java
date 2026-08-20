package com.prueba.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties(prefix = "app.service")
public record ServiceProperties (
   String endpoint,
   String secretkey
){}

