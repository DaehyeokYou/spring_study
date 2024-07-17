package com.ydh.springstudy.Controller;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/mono")
public class MonoController {

    @GetMapping("/just")
    public Mono<String> getMonoJust() {
        return Mono.just("Mono.just()");
    }

    @GetMapping("/empty")
    public Mono<String> getMonoEmpty() {
        return Mono.empty();
    }

    @GetMapping("/fromCallable")
    public Mono<String> getMonoFromCallable() {
        return Mono.fromCallable(() -> "Mono.fromCallable()");
    }

//    @GetMapping("/fromFuture")
//    public Mono<String> getMonoFromFuture() {
//        return Mono.fromFuture()
//    }

    @GetMapping("/fromSupplier")
    public Mono<Double> getMonoFromSupplier() {
        return Mono.fromSupplier(new Random()::nextDouble);
    }

    @GetMapping("/fromMono")
    public Mono<String> getMonoFromMono() {
        return Mono.from(Mono.just("fromMono"));
    }

    @GetMapping("/fromFlux")
    public Mono<Integer> getMonoFromFlux() {
        return Mono.from(Flux.range(1,10));
    }
}
