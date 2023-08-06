package com.cost.costcalculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CostCalculatorConfig {

    @Bean("binTableWebClient")
    public WebClient binTableClient() {
        return WebClient
                .builder()
                .baseUrl("https://api.bintable.com/")
                .build();
    }
}
