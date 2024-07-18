package com.ydh.springstudy.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
class WebFluxController {

    final String str;

    public WebFluxController() {
        str = "Hello Webflux!";
    }

    @GetMapping({"/mono/{val}",
                "/mono"})
    public Mono<String> getMono(@PathVariable(required = false) String val) {
        if(val == null) { // val가 null이면, Hello WebFlux
            System.out.println(this.str);
            return Mono.just(this.str);
        }
        System.out.println(val);
        return Mono.just(val);
    }

    @GetMapping({"/flux/{val}",
            "/flux"})
    public Flux<String> getFlux(@PathVariable(required = false) String val) {
        if(val == null) {
            System.out.println(this.str);
            return Flux.just(this.str);
        }
        System.out.println(val);
        return Flux.just(val);
    }
}