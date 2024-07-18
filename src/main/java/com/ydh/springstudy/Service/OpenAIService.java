package com.ydh.springstudy.Service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ydh.springstudy.payload.OpenAIRequest;
import com.ydh.springstudy.payload.OpenAIResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class OpenAIService {
    private final WebClient openAIWebClient;

    /*
    //lombok:AllArgsConstructor으로 대체 가능
    public OpenAIService(WebClient openAIwebClient) {
        this.openAIWebClient = openAIwebClient;
    }
    */

    private OpenAIRequest createOpenAIRequest(String question) {
        OpenAIRequest payload = new OpenAIRequest();
        OpenAIRequest.Message message = new OpenAIRequest.Message();
        message.setRole("user");
        message.setContent(question);
        payload.setMessages(List.of(message));
        return payload;
    }

    public Mono<String> getChatService(String question) {
        /*
        OpenAIRequest payload = new OpenAIRequest(question);    //"hi"

        return openAIWebClient.post()
                .uri("/openai/deployments/gpt-35-turbo-1106/chat/completions?api-version=2024-02-15-preview")
                .body(Mono.just(payload), OpenAIRequest.class)
                .retrieve()
                .bodyToMono(String.class);
         */
        return openAIWebClient.post()
                .uri("/openai/deployments/gpt-35-turbo-1106/chat/completions?api-version=2024-02-15-preview")
                .bodyValue(createOpenAIRequest(question))
                .retrieve()
                .bodyToMono(OpenAIResponse.class) // JSON 응답을 OpenAIResponse 객체로 변환합니다.
                .flatMap(response -> Mono.just(response.getChoices().get(0).getMessage().getContent())); // 원하는 데이터를 추출합니다.
    }

    public Mono<String> postChatService(OpenAIRequest request) {
        return openAIWebClient.post()
                .uri("/openai/deployments/gpt-35-turbo-1106/chat/completions?api-version=2024-02-15-preview")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(OpenAIResponse.class) // JSON 응답을 OpenAIResponse 객체로 변환합니다.
                .flatMap(response -> Mono.just(response.getChoices().get(0).getMessage().getContent())); // 원하는 데이터를 추출합니다.
    }
    public Mono<String> postChatService(String question) {
        return openAIWebClient.post()
                .uri("/openai/deployments/gpt-35-turbo-1106/chat/completions?api-version=2024-02-15-preview")
                .bodyValue(createOpenAIRequest(question))
                .retrieve()
                .bodyToMono(OpenAIResponse.class) // JSON 응답을 OpenAIResponse 객체로 변환합니다.
                .flatMap(response -> Mono.just(response.getChoices().get(0).getMessage().getContent())); // 원하는 데이터를 추출합니다.
    }

}
