package com.cost.costcalculator.web_client;

import com.cost.costcalculator.dto.BinTableCardDetailsResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class BinTableClient {

    @Value("${bin.table.api.key}")
    private String binTableApiKey;

    private final WebClient binTableWebClient;

    public BinTableClient(WebClient binTableWebClient) {
        this.binTableWebClient = binTableWebClient;
    }

    public Mono<BinTableCardDetailsResponseDto> getDetailsFromCardNumber(String cardNumber) {
        StringBuilder uriBuilder = new StringBuilder();
        uriBuilder.append("v1/");
        uriBuilder.append(cardNumber);
        uriBuilder.append("?api_key=");
        uriBuilder.append(binTableApiKey);
        String uri = uriBuilder.toString();
        Mono<BinTableCardDetailsResponseDto> response = binTableWebClient
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(BinTableCardDetailsResponseDto.class);
        return response;
    }
}
