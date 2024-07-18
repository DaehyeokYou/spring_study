package com.ydh.springstudy.Controller;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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

    @GetMapping("/create")
    public Flux<Double> getCreate() {
        return Flux.create(emitter -> {
            Random rnd = new Random();
            for(int i = 0; i <= 10; i++)
                emitter.next(rnd.nextDouble());

            int random = rnd.nextInt(2);

            if(random < 1) emitter.complete();
            else emitter.error(new RuntimeException("Bad luck, you had one change out of 2 to complete Flux"));
        });
    }

    class Product {
        private String name;
        private int amount ;

        // Constructor
        public Product() {
            this.name = "Unknown";
            this.amount = 0;
        }

        public Product(String name, int amount) {
            this.name = name;
            this.amount = amount;
        }

        // Getters: Used when Jackson Serialize
        public String getName() {
            return name;
        }

        public double getamount() {
            return amount;
        }

    }

    @GetMapping("/convertmono")
    public Flux<Product> getConvertMono() {
        System.out.println("flatMapIterable!");
        Map<Integer, List<Product>> mp = new HashMap<>();
        mp.put(1, Arrays.asList(new Product(), new Product("a",2), new Product("b",3)));
        mp.put(2, Arrays.asList(new Product("x", 10), new Product("y",11), new Product("z",12)));

        Mono<Map<Integer, List<Product>>> monoWithMap = Mono.just(mp);
        return monoWithMap.flatMapIterable(m -> m.get(1));
    }

}
