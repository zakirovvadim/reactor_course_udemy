package ru.vadim.java.reactive.cource.tutorial;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec03FluxTest {

    public Flux<Integer> getItems() {
        return Flux.just(1, 2,3)
                .log();
    }

    @Test
    public void fluxTest() {
        StepVerifier.create(getItems(), 1) // без второго аргумента длины, будет запрашивать все, с ограничением не поймает комплит сигнал
                .expectNext(1)
                .thenCancel()// так как мы запросили один элемент и не получим комплит сигнал, и все элементы также не можем получить, можно вызвать отмену.
                .verify();
    }

    @Test
    public void fluxTest2() {
        StepVerifier.create(getItems())
                .expectNext(1)// так как продьюсятся три элемента нужно проверять их всех, иначе после первого мы ожидаем сразу комплит, но там еще 2 элемента. Проверка идет пошагово. Порядок проверки также важен.
                .expectNext(2)
                .expectNext(3)
                .expectComplete()
                .verify();
    }

    @Test
    public void fluxTest3() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3)
                .expectComplete()
                .verify();
    }
}
