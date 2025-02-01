package ru.vadim.java.reactive.cource.tutorial.sec09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
/*
concatDelayError  откладывает возникновение ошибки в конец
 */

@Slf4j
public class Lec04ConcatError {
    public static void main(String[] args) throws InterruptedException {
        demo2();
        Thread.sleep(Duration.ofSeconds(10));
    }

    private static void demo1() {
        producer1()
                .concatWith(producer3())
                .concatWith(producer2())
                .subscribe(ru.vadim.updatedCource.common.Util.subscriber());
    }

    private static void demo2() {
        Flux.concatDelayError(producer1(), producer3(), producer2())
                .subscribe(ru.vadim.updatedCource.common.Util.subscriber());
    }

    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                .doOnSubscribe(s -> log.info("subscribing to producer1"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2() {
        return Flux.just(51, 52, 53)
                .doOnSubscribe(s -> log.info("subscribing to producer2"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer3() {
        return Flux.error(new RuntimeException("oops"));
    }
}
