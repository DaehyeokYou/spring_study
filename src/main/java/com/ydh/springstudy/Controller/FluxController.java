package com.ydh.springstudy.Controller;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import static reactor.core.publisher.Flux.fromStream;

@RestController
@RequestMapping("/api/v1/flux")
public class FluxController {

    @GetMapping("/just")
    public Flux<String> getFluxJust() {
        return Flux.just("Flux.just()", "one more string", "two more sting");
    }

    @GetMapping("/empty")
    public Flux<String> getFluxEmpty() {
        return Flux.empty();
    }

    @GetMapping("/range")
    public Flux<Integer> getFluxRange() {
        return Flux.range(2,10);
    }

    @GetMapping("/interval")
    public Flux<Long> getFluxInterval() {
        return Flux.interval(Duration.ofMillis(10));
    }

    @GetMapping("/fromIterable")
    public Flux<String> getFluxFromIterable() {
        //Iterable한 요소로부터 Flux를 생성합니다. 여기서는 List가 사용되었습니다.
        List<String> stringList = Arrays.asList("Hello", "Iterable");
        return Flux.fromIterable(stringList);
    }

    @GetMapping("/fromStream")
    public Flux<String> getFluxFromStream() {
        //Java Stream에서도 동일하게 동작합니다.
        //Stream<String> stringStream = Arrays.asList("Hello", "foo", "bar").stream();
        Stream<String> stringStream = Stream.of("Hello", "Stream");
        return Flux.fromStream(stringStream);
    }

    @GetMapping("/fromFlux")
    public Flux<String> getFluxFromFlux() {
        return Flux.from(Flux.just("i", "am", "flux"));
    }
}