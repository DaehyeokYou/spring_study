package com.ydh.springstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.ydh.springstudy.config.CortexProperties;
import com.ydh.springstudy.config.OpenAIProperties;
import com.ydh.springstudy.config.OpenAIWebClientConfig;

@SpringBootApplication
@EnableConfigurationProperties({CortexProperties.class})
public class SpringstudyApplication {
	/*
	flatMapIterable
	webclient
	fromIterable
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringstudyApplication.class, args);
	}

}
