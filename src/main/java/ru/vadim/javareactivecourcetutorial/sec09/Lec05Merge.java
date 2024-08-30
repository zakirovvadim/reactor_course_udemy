package ru.vadim.javareactivecourcetutorial.sec09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import java.time.Duration;
import java.util.List;

/*
merge() - позволяет объединять производителей. Потребление в таком случае идет рандомно. Если использовать ограничение на
потребление в виде оператора take() то потребятся только указанное количество элементов, успевших произвестись каким-либо производителем
 */
@Slf4j
public class Lec05Merge {
    public static void main(String[] args) {

        demo2();

        Util.sleepSeconds(3);
    }

    private static void demo1() {
        Flux.merge(producer1(), producer2(), producer3())
                .take(2)
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        producer1()
                .mergeWith(producer2())
                .mergeWith(producer3())
                .take(2)
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                .transform(ru.vadim.updatedCource.common.Util.fluxLogger("producer3"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2() {
        return Flux.just(51, 52, 53)
                .transform(ru.vadim.updatedCource.common.Util.fluxLogger("producer3"))
                .delayElements(Duration.ofMillis(10));
    }
    private static Flux<Integer> producer3() {
        return Flux.just(11, 22, 33)
                .transform(ru.vadim.updatedCource.common.Util.fluxLogger("producer3"))
                .delayElements(Duration.ofMillis(10));
    }
}
