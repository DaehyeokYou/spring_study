package com.ydh.springstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CortexRulerWebClientConfig {
    private final CortexProperties cortexProperties;
    // webClient Bean 등록
    @Bean
    public WebClient CortexRulerWebClient(WebClient.Builder builder) {
        String url = cortexProperties.getUrl();
        String apikey = cortexProperties.getApikey();
        return builder
                .baseUrl(url) // 호출할 API 서비스 도메인 URL
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + apikey);
                })
                // ... 기타 설정 필요하면 추가 ...
                .exchangeStrategies(ExchangeStrategies.builder()
                                                        .codecs(config -> config.defaultCodecs()
                                                                        .maxInMemorySize(10*1024*1024))
                                                        .build())
                .build();
    }
}
