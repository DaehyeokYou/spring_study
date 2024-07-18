package com.ydh.springstudy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ydh.springstudy.Service.OpenAIService;
import com.ydh.springstudy.payload.OpenAIRequest;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@AllArgsConstructor
@RestController
@RequestMapping("/openai")
public class OpenAIController {

    private final OpenAIService openAIService;
    private final ObjectMapper objectMapper;
    /*
    // final 변수는 꼭! Constructor injection (lombok:AllArgsConstructor)으로 대체 가능)
    @Autowired
    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }
    */
    @GetMapping("/chat/{question}")
    public Mono<String> getChat(@PathVariable String question) {
        return openAIService.getChatService(question);
    }


    @PostMapping("/chat")
    public Mono<String> postChat(@RequestBody OpenAIRequest request) {
        return openAIService.postChatService(request);
    }

    @SneakyThrows
    @PostMapping("/chathybrid/sync")
    public Mono<String> postChat(@RequestHeader(HttpHeaders.CONTENT_TYPE) String contentType, @RequestBody String body) {
        if ("text/plain".equalsIgnoreCase(contentType)) {
            return openAIService.postChatService(body);
        } else if ("application/json".equalsIgnoreCase(contentType)) {
            OpenAIRequest request = objectMapper.readValue(body, OpenAIRequest.class);
            return openAIService.postChatService(request);
        } else {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Unsupported Content-Type");
        }
    }

    @PostMapping("/chathybrid/async")
    public Mono<String> postChat2(@RequestHeader(HttpHeaders.CONTENT_TYPE) String contentType, @RequestBody Mono<String> body) {
        return body.flatMap(b -> {
            if ("text/plain".equalsIgnoreCase(contentType)) {
                return openAIService.postChatService(b);
            } else if ("application/json".equalsIgnoreCase(contentType)) {
                return Mono.fromCallable(() -> objectMapper.readValue(b, OpenAIRequest.class))
                        .subscribeOn(Schedulers.boundedElastic())
                        .flatMap(request -> openAIService.postChatService(request));
            } else {
                throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Unsupported Content-Type");
            }
        });
    }
}
