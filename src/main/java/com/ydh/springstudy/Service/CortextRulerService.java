package com.ydh.springstudy.Service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ydh.springstudy.payload.OpenAIResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@Service
public class CortextRulerService {

    private final WebClient cortexRulerWebClient;

    public CortextRulerService(@Qualifier("CortexRulerWebClient") WebClient cortexRulerWebClient) {
        this.cortexRulerWebClient = cortexRulerWebClient;
    }

    public Mono<String> getCortexRules() {
        return cortexRulerWebClient.get()
                .uri("/api/v1/rules")
                .retrieve()
                .bodyToMono(String.class); // application/yaml 응답을 OpenAIResponse 객체로 변환합니다.
    }
}
