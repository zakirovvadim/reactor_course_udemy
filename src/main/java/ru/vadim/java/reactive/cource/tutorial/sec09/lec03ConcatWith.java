package ru.vadim.java.reactive.cource.tutorial.sec09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.time.Duration;

/*
.concatWith противоположный аналог .startWith, т.е. выполняется потребление основного продьюсера,а потом уже того что в concatWith
concatWith - принимает другой продьюсер
concatWithValues - обычные значения
 */
@Slf4j
public class lec03ConcatWith {

    public static void main(String[] args) throws InterruptedException {
        demo3();
        Thread.sleep(Duration.ofSeconds(3));
    }

    public static void demo1() {
        producer1()
                .concatWithValues(-1, 0)
                .subscribe(Util.subscriber());
    }

    public static void demo2() {
        producer1()
                .concatWith(producer2())
                .take(4)
                .subscribe(Util.subscriber());
    }

    public static void demo3() {
        Flux.concat(producer1(), producer2())
                .subscribe(Util.subscriber());
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
}
