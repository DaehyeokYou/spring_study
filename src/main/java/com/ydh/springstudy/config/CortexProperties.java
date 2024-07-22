package com.ydh.springstudy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@ConfigurationProperties(prefix = "cortex")
@RequiredArgsConstructor
@ConstructorBinding
@Getter
public class CortexProperties {
    private final String url;
    private final String apikey;
}
