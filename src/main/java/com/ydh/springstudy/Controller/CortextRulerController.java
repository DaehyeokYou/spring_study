package com.ydh.springstudy.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ydh.springstudy.Service.CortextRulerService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/cortex/ruler")
public class CortextRulerController {
    private final CortextRulerService cortextRulerService;

    @GetMapping("/rules")
    public Mono<String> getCortexRules() {
        return cortextRulerService.getCortexRules();
    }
}
