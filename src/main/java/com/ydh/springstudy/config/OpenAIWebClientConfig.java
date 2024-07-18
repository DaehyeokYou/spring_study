package com.ydh.springstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OpenAIWebClientConfig {
    // webClient Bean 등록
    @Bean
    public WebClient OpenAIwebClient(WebClient.Builder builder) {
        String url = "https://oai-demo-canada.openai.azure.com/";
        String apikey = System.getenv("OPENAI_API_KEY");
        return builder
                .baseUrl(url) // 호출할 API 서비스 도메인 URL
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.add("api-key", apikey);
                })
                // ... 기타 설정 필요하면 추가 ...
                .exchangeStrategies(ExchangeStrategies.builder()
                                                        .codecs(config -> config.defaultCodecs().
                                                                                maxInMemorySize(10*1024*1024))
                                                        .build())
                .build();
    }
}
