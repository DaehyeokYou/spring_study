package com.ydh.springstudy.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
class WebFluxController {

    final String str;

    public WebFluxController() {
        str = "Hello Webflux!";
    }

    @GetMapping("/a")
    public Mono<String> getAlertmanagerConfig() {
        return Mono.just(str);
    }
}