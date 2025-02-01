package ru.vadim.java.reactive.cource.tutorial;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

public class Lec04RangeTest {

    public Flux<Integer> getItems() {
        return Flux.range(1, 50);
    }

    public Flux<Integer> getRandomItems() {
        return Flux.range(1, 50)
                .map(i -> Util.faker().random().nextInt(1, 100));
    }


    @Test
    public void rangeTest1() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3)
                .expectNextCount(47)
                .expectComplete()
                .verify();
    }

    @Test
    public void rangeTest2() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3)
                .expectNextCount(22)
                .expectNext(26, 27,28)
                .expectNextCount(22)
                .expectComplete()
                .verify();
    }

    @Test
    public void rangeTestRandom() {
        StepVerifier.create(getRandomItems())
                .expectNextMatches(i -> i > 0 && i < 101)// в этом случае мы првоеряем только один элемент
                .expectNextCount(49)// дальше пропускаем остальные элементы и ловим уже сигнал комплит
                .expectComplete()
                .verify();
    }

    @Test
    public void rangeTestRandom2() {
        StepVerifier.create(getRandomItems())
                .thenConsumeWhile(i -> i > 0 && i < 101)// проверяем, что чтобы ни продьюсилось должно соответствовать условию
                .expectComplete()
                .verify();
    }
}
