package ru.vadim.javareactivecourcetutorial.section02.client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.vadim.javareactivecourcetutorial.courceUtil.AbstractHttpClient;
import ru.vadim.updatedCource.common.Util;

import java.net.URI;
import java.net.http.HttpRequest;

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
                .uri("http://localhost:8070/demo02/stock/stream")
                .responseContent()
                .asString();
    }

    public Flux<String> getProductService(Integer id) {
        return this.httpClient.get()
                .uri("http://localhost:8070/demo03/product/" + id)
                .responseContent()
                .asString();
    }

    public Flux<String> getEmptyFallbackProduct(Integer id) {
        return this.httpClient.get()
                .uri("http://localhost:8070/demo03/empty-fallback/product/" + id)
                .responseContent()
                .asString();
    }

    public Flux<String> getTimeoutFallbackProduct(Integer id) {
        return this.httpClient.get()
                .uri("http://localhost:8070/demo03/timeout-fallback/product/" + id)
                .responseContent()
                .asString();
    }

    public Flux<String> getOrdersStream() {
        return this.httpClient.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString();
    }

    public Mono<String> demo05GetProduct(Integer id) {
        return this.httpClient.get()
                .uri("/demo05/product/" + id)
                .responseContent()
                .asString()
                .next();
    }

    public Mono<String> demo05GetPrice(Integer id) {
        return this.httpClient.get()
                .uri("/demo05/price/" + id)
                .responseContent()
                .asString()
                .next();
    }

    public Mono<String> demo05GetReview(Integer id) {
        return this.httpClient.get()
                .uri("/demo05/review/" + id)
                .responseContent()
                .asString()
                .next();
    }
}
