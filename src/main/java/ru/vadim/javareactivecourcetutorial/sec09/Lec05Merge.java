package ru.vadim.javareactivecourcetutorial.sec09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import java.time.Duration;
import java.util.List;

@Slf4j
public class Lec05Merge {
    public static void main(String[] args) {


        Util.sleepSeconds(3);
    }

    public static void demo2() {
        producer1()
                .startWith(List.of(-2, -1, 0))
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
    private static Flux<Integer> producer3() {
        return Flux.just(11, 22, 33)
                .doOnSubscribe(s -> log.info("subscribing to producer2"))
                .delayElements(Duration.ofMillis(10));
    }
}
