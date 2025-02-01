package ru.vadim.java.reactive.cource.tutorial;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@Slf4j
public class Lec01MonoTest {

    private Mono<String> getProduct(int id) {
        return Mono.fromSupplier(() -> "product-" + id)
                .doFirst(() -> log.info("invoked"));
    }

    @Test
    public void productTest() {
        StepVerifier.create(getProduct(1))
                .expectNext("product-1")
                .expectComplete()
                .verify();
    }
}
