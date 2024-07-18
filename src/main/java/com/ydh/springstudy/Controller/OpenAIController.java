package com.ydh.springstudy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydh.springstudy.Service.OpenAIService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/openai")
public class OpenAIController {

    private final OpenAIService openAIService;
    /*
    // final 변수는 꼭! Constructor injection (lombok:AllArgsConstructor)으로 대체 가능)
    @Autowired
    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }
    */
    @GetMapping("/chat/{question}")
    public Mono<String> getChat(@PathVariable String question) {
        return openAIService.chatService(question);
    }
}
