package com.ydh.springstudy.Controller;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

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

    @GetMapping("/fromSupplier")
    public Mono<Double> getMonoFromSupplier() {
        return Mono.fromSupplier(new Random()::nextDouble);
    }

    @GetMapping("/fromFuture")
    public Mono<String> getMonoFromFuture() {
        CompletableFuture<Void> myFuture = CompletableFuture.runAsync(() -> {
            // runAsync는 매개변수: Runnable Functional Interface를 받음
            // Runnable은 반환값이 X (Callable은 반환값 O)
            System.out.println("my future");
        });
        CompletableFuture<String> helloWorldFuture = CompletableFuture.supplyAsync(() -> {
            // supplyAsync는 매개변수: Supplier Functional Interface를 받음
            return "Hello Future, Current Thread: " + Thread.currentThread().getName();
        });
        return Mono.fromFuture(helloWorldFuture);
    }

    @GetMapping("/fromMono")
    public Mono<String> getMonoFromMono() {
        return Mono.from(Mono.just("fromMono"));
    }

    @GetMapping("/fromFlux")
    public Mono<Integer> getMonoFromFlux() {
        return Mono.from(Flux.range(2,10));
    }
}
