package com.ydh.springstudy.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket defaultApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ydh.springstudy.Controller"))
                .paths(PathSelectors.any())
                .build()
                .genericModelSubstitutes(Optional.class, Mono.class, Flux.class);   // webflux관련
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Spring WebFlux API")
                .version("1.0")
                .description("API documentation for Spring WebFlux application")
                .build();
    }


}
