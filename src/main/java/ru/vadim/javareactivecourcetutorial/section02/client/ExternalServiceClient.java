package ru.vadim.javareactivecourcetutorial.section02.client;

import reactor.core.publisher.Flux;
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

    public Flux<String> getNames() {
        return this.httpClient.get()
                .uri("http://localhost:8070/demo02/name/stream")
                .responseContent()
                .asString();
    }

    public Flux<String> getCurrency() {
        return this.httpClient.get()
                .uri("http://localhost:8090/demo02/stock/stream")
                .responseContent()
                .asString();
    }
}
