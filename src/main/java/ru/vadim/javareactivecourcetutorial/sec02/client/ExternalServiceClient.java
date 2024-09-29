package ru.vadim.javareactivecourcetutorial.sec02.client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.vadim.javareactivecourcetutorial.courceUtil.AbstractHttpClient;
import ru.vadim.javareactivecourcetutorial.sec09.helper.Product;

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
                .uri("http://localhost:8080/demo02/name/stream")
                .responseContent()
                .asString();
    }

    public Flux<String> getCurrency() {
        return this.httpClient.get()
                .uri("http://localhost:8080/demo02/stock/stream")
                .responseContent()
                .asString();
    }

    public Flux<String> getProductService(Integer id) {
        return this.httpClient.get()
                .uri("http://localhost:8080/demo03/product/" + id)
                .responseContent()
                .asString();
    }

    public Flux<String> getEmptyFallbackProduct(Integer id) {
        return this.httpClient.get()
                .uri("http://localhost:8080/demo03/empty-fallback/product/" + id)
                .responseContent()
                .asString();
    }

    public Flux<String> getTimeoutFallbackProduct(Integer id) {
        return this.httpClient.get()
                .uri("http://localhost:8080/demo03/timeout-fallback/product/" + id)
                .responseContent()
                .asString();
    }


    public Flux<String> getOrdersStream() {
        return this.httpClient.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString();
    }

    public Mono<String> getProductName(Integer id) {
        return get("/demo05/product/" + id);
    }

    public Mono<String> getPrice(Integer id) {
        return get("/demo05/price/" + id);
    }

    public Mono<String> getReview(Integer id) {
        return get("/demo05/review/" + id);
    }

    public Mono<String> get(String path) {
        return this.httpClient.get()
                .uri(path)
                .responseContent()
                .asString()
                .next();
    }

    public Mono<Product> getProduct(int productId) {
        return Mono.zip(
                        getProductName(productId),
                        getPrice(productId),
                        getReview(productId)
                )
                .map(t -> new Product(t.getT1(), t.getT2(), t.getT3()));
    }

}
