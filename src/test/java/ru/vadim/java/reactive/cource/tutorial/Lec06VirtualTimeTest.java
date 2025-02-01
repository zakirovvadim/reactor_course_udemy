package ru.vadim.java.reactive.cource.tutorial;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec06VirtualTimeTest {

    public Flux<Integer> getItems() {
        return Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(10));
    }

    // В этом варианте мы будем ожидать продьюсера с его задержкой в 10 секунд на кжадый элемент
    @Test
    public void rangeTest1() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();
    }

    /*
    Симулируем обратный отсчет, и как бы спрашиваем, дай элементы после "выполнения" таймингов задержки
     */
    @Test
    public void virtualTimeTest1() {
        StepVerifier.withVirtualTime(this::getItems)
                .thenAwait(Duration.ofSeconds(51))
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();
    }

    @Test
    public void virtualTimeTest2() {
        StepVerifier.withVirtualTime(this::getItems)// симулируй время
                .expectSubscription() // так как в остальных случаях мы подписывались и получали сразу элементы для проверки, нам не требовалось ожидать отдельно сигнал подписки, это уже подразумевалось, но тут мы хотим ничего не ожидать 9 секунд, но подписка все равно затрегерит это ожидание и он упадет с ошибкой, поэтому в первую очередь проверяем сигнал подписки
                .expectNoEvent(Duration.ofSeconds(9))// ожидай что в первые 9 секунд ни будет никаких сигналов
                .thenAwait(Duration.ofSeconds(1))// подожди еще секунду
                .expectNext(1)// ожидай первый в порядке элемент
                .thenAwait(Duration.ofSeconds(40))// снова симулируй ожидание задержки в 40 секунд
                .expectNext(2, 3, 4, 5)// ожидай оставшиеся элементы
                .expectComplete()
                .verify();
    }
}
