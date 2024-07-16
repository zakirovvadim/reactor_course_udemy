package ru.vadim.javareactivecourcetutorial.section02.client;

import reactor.core.publisher.Mono;
import ru.vadim.javareactivecourcetutorial.courceUtil.AbstractHttpClient;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .next();
    }
}
