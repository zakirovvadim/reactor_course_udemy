package ru.vadim.java.reactive.cource.tutorial.sec07.client;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.AbstractHttpClient;

@Slf4j
public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getProductName(int productId) {
        return this.httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .doOnNext(m -> log.info("next: {}", m))
                .next()
                .publishOn(Schedulers.boundedElastic()); // если убрать, и консьюмер будет потреблять последовательно с задержкой, то и запросы будут идти последовательно.
    }



    public Flux<String> getCountry() {
        return this.httpClient.get()
                .uri("/demo06/country")
                .responseContent()
                .asString();
    }

    public Flux<String> getProduct(int productId) {
        return this.httpClient.get()
                .uri("/demo06/product/" + productId)
                .responseContent()
                .asString();
    }
}
