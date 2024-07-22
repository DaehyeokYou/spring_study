package com.ydh.springstudy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@ConfigurationProperties(prefix = "openai")
@RequiredArgsConstructor
@ConstructorBinding
@Getter
public class OpenAIProperties {
    private final String url;
    private final String apikey;
}
