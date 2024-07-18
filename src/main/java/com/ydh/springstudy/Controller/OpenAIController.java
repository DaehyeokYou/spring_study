package com.ydh.springstudy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydh.springstudy.Service.OpenAIService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/openai")
public class OpenAIController {

    private final OpenAIService openAIService;

    // final 변수는 꼭! Constructor injection (lombok으로 대체 가능)
    @Autowired
    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping("/chat/{question}")
    public Mono<String> getChat(@PathVariable String question) {
        return openAIService.chatService(question);
    }
}
